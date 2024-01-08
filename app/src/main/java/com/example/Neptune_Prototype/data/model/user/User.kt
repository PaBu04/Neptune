package com.example.Neptune_Prototype.data.model.user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.Neptune_Prototype.data.model.backend.BackendConnector
import com.example.Neptune_Prototype.data.model.backend.ParticipantBackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.ui.commons.TrackListType
import java.util.HashMap

abstract class User(
    private val session: Session,
    private val backendConnector: BackendConnector
) {

    private val relevantTracks = HashMap<String, MutableState<Track>>()

    val voteList by mutableStateOf(mutableStateListOf<MutableState<Track>>())

    val blockedList by mutableStateOf(mutableStateListOf<MutableState<Track>>())

    val cooldownList by mutableStateOf(mutableStateListOf<MutableState<Track>>())

    val searchList by mutableStateOf(mutableStateListOf<MutableState<Track>>())


    protected fun addRelevantTrack(track: Track) {
        relevantTracks[track.spotifyId] = mutableStateOf(track)
    }

    protected fun isTrackRelevant(spotifyTrackId: String): Boolean {
        return relevantTracks.containsKey(spotifyTrackId)
    }

    protected fun getRelevantTrack(spotifyTrackId: String): MutableState<Track>? {
        return relevantTracks[spotifyTrackId]
    }

    protected fun removeFromRelevantTracks(spotifyTrackId: String) {
        relevantTracks.remove(spotifyTrackId)
    }


    protected fun addTrackToVoteList(track: Track) {
        if (!isTrackRelevant(track.spotifyId)) {
            addRelevantTrack(track)
        }
        voteList.add(getRelevantTrack(track.spotifyId)!!)
        backendConnector.addTrackToSession(track)
    }

    protected fun removeTrackFromVoteList(index: Int){
        voteList.removeAt(index)
    }

    fun addUpvoteToTrack(track: Track) {
        if (isTrackRelevant(track.spotifyId)) {
            val relevantTrack = getRelevantTrack(track.spotifyId)!!
            relevantTrack.value.isUpvoted = true
            relevantTrack.value.upvoteCount++
            if(!isTrackInVoteList(track)){
                addTrackToVoteList(track)
            }
        } else {
            track.isUpvoted = true
            track.upvoteCount++
            addTrackToVoteList(track)
        }
        backendConnector.addUpvoteToTrack(track)
    }

    fun removeUpvoteFromTrack(track: Track) {
        if (isTrackRelevant(track.spotifyId)) {
            val relevantTrack = getRelevantTrack(track.spotifyId)!!
            relevantTrack.value.isUpvoted = false
            relevantTrack.value.upvoteCount--
        } else {
            track.isUpvoted = false
            track.upvoteCount--
        }
        backendConnector.removeUpvoteFromTrack(track)
    }

    fun joinSession(sessionId: Int) {
        (backendConnector as ParticipantBackendConnector).participantJoinSession(sessionId)
    }

    fun leaveSession() {
        (backendConnector as ParticipantBackendConnector).participantLeaveSession()
    }

    fun updateTrackData() {
        backendConnector.getAllTrackData { tracks ->
            updateTrackDataCallback(tracks)
        }
    }

    private fun updateTrackDataCallback(tracks: List<Track>) {
        tracks.forEach {
            if(isTrackRelevant(it.spotifyId)){
                getRelevantTrack(it.spotifyId)!!.value.upvoteCount = it.upvoteCount
                //TODO komplett überarbeiten
            }
            if (!isTrackInVoteList(it)) {
                addTrackToVoteList(it)
            }
        }
    }

    protected fun isTrackInVoteList(track: Track): Boolean {
        var isTrackInVoteList = false
        voteList.forEach {
            if (it.value.spotifyId == track.spotifyId) {
                isTrackInVoteList = true
            }
        }
        return isTrackInVoteList
    }

    protected fun isTrackInBlockedList(track: Track): Boolean {
        var isTrackInBlockedList = false
        blockedList.forEach {
            if (it.value.spotifyId == track.spotifyId) {
                isTrackInBlockedList = true
            }
        }
        return isTrackInBlockedList
    }

    private fun clearVoteList(){
        voteList.clear()
    }

    abstract suspend fun searchTracks(searchInput: String)

}