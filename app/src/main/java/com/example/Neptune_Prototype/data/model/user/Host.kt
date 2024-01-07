package com.example.Neptune_Prototype.data.model.user

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.Neptune_Prototype.data.model.backend.BackendConnector
import com.example.Neptune_Prototype.data.model.backend.HostBackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackListType
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Host(
    private val session: Session,
    private val hostBackendConnector: HostBackendConnector,
    private val spotifyConnector: SpotifyConnector
) : FullParticipant(session, hostBackendConnector, spotifyConnector) {

    val queueList by mutableStateOf(mutableStateListOf<TrackUiInstance>())

    var playbackState = mutableStateOf(PlaybackState.NONE)

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
            val trackUiInstanceToAdd =
                TrackUiInstance(mutableStateTrackToAdd, TrackListType.HOST_SEARCH)
            searchList.add(trackUiInstanceToAdd)
        }
    }


    override fun addTrackToVoteList(track: Track) {
        internalAddTrackToVoteList(track, TrackListType.HOST_VOTE)
    }


    fun addTrackToQueueList(track: Track) {
        if (!isTrackRelevant(track.spotifyId)) {
            addRelevantTrack(track)
        }
        val trackUiInstance =
            TrackUiInstance(getRelevantTrack(track.spotifyId)!!, TrackListType.HOST_QUEUE)
        queueList.add(trackUiInstance)
        if (queueList.size == 1) {
            GlobalScope.launch {
                spotifyConnector.playTrack(track.spotifyId)
            }
        }
    }

    fun removeFromQueueList(trackUiInstance: TrackUiInstance) {
        queueList.remove(trackUiInstance)
        if (queueList.size == 0) {
            pausePlayback()
        }
        if (!isTrackInVoteList(trackUiInstance.track.value) && !isTrackInBlockedList(trackUiInstance.track.value)) {
            removeFromRelevantTracks(trackUiInstance.track.value.spotifyId)
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
                spotifyConnector.playTrack(queueList[0].track.value.spotifyId)
            }
        } else {
            endPlayback()
        }
    }

}