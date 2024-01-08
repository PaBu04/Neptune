package com.example.Neptune_Prototype.data.model.user

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.Neptune_Prototype.data.model.backend.HostBackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.ui.commons.TrackListType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Host(
    private val session: Session,
    private val hostBackendConnector: HostBackendConnector,
    private val spotifyConnector: SpotifyConnector
) : FullParticipant(session, hostBackendConnector, spotifyConnector) {

    val queueList by mutableStateOf(mutableStateListOf<MutableState<Track>>())

    var playbackState = mutableStateOf(PlaybackState.NONE)


    fun createNewSession() {
        //hostBackendConnector.getUserSessionState { Log.i("RES", it) }
        hostBackendConnector.createNewSession { sessionId, timestamp ->
            createNewSessionCallback(sessionId, timestamp)
        }
    }

    private fun createNewSessionCallback(sessionId: Int, timestamp: Int) {
        session.setSessionIdentifiers(sessionId, timestamp)
        Log.i("SESSION ID", sessionId.toString())
        Log.i("TIMESTAMP", timestamp.toString())
    }

    fun deleteSession() {
        hostBackendConnector.deleteSession()
    }


    override suspend fun searchTracks(searchInput: String) {
        if (searchInput == "") {
            return
        }
        val searchResultTrackList = spotifyConnector.searchTracks(searchInput)
        searchList.clear()
        searchResultTrackList.forEach {
            val mutableStateTrackToAdd =
                if (isTrackRelevant(it.spotifyId)) getRelevantTrack(it.spotifyId)!!
                else mutableStateOf(it)
            searchList.add(mutableStateTrackToAdd)
        }
    }



    fun addTrackToQueueList(track: Track) {
        if (!isTrackRelevant(track.spotifyId)) {
            addRelevantTrack(track)
        }
        queueList.add(getRelevantTrack(track.spotifyId)!!)
        if (queueList.size == 1) {
            GlobalScope.launch {
                spotifyConnector.playTrack(track.spotifyId)
            }
        }
    }

    fun removeFromQueueList(index: Int) {
        val removedTrack = queueList.removeAt(index)
        if (queueList.size == 0) {
            pausePlayback()
        }
        if (!isTrackInVoteList(removedTrack.value) && !isTrackInBlockedList(removedTrack.value)) {
            removeFromRelevantTracks(removedTrack.value.spotifyId)
        }
    }

    fun pausePlayback() {
        playbackState.value = PlaybackState.PAUSED
        GlobalScope.launch {
            spotifyConnector.pause()
        }
    }

    fun endPlayback() {
        playbackState.value = PlaybackState.NONE
        GlobalScope.launch {
            spotifyConnector.pause()
        }
    }

    fun resumePlayback() {
        if (queueList.size > 0) {
            playbackState.value = PlaybackState.PLAYING
            GlobalScope.launch {
                spotifyConnector.resume()
            }
        }
    }

    fun skipTrack() {
        if (queueList.size > 0) {
            queueList.removeAt(0)
        }
        if (queueList.size > 0) {
            GlobalScope.launch {
                spotifyConnector.playTrack(queueList[0].value.spotifyId)
            }
        } else {
            endPlayback()
        }
    }

}