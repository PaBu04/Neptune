package com.example.Neptune_Prototype.data.model.user

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.Neptune_Prototype.data.model.app.SpotifyLevel
import com.example.Neptune_Prototype.data.model.backend.BackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackListType
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.HashMap

abstract class User(
    private val session: Session,
    private val backendConnector: BackendConnector
) {

    private val relevantTracks = HashMap<String, MutableState<Track>>()

    val voteList by mutableStateOf(mutableStateListOf<TrackUiInstance>())

    val blockedList by mutableStateOf(mutableStateListOf<TrackUiInstance>())

    val searchList by mutableStateOf(mutableStateListOf<TrackUiInstance>())


    fun addRelevantTrack(track: Track) {
        relevantTracks[track.spotifyId] = mutableStateOf(track)
    }

    fun isTrackRelevant(spotifyTrackId: String): Boolean {
        return relevantTracks.containsKey(spotifyTrackId)
    }

    fun getRelevantTrack(spotifyTrackId: String): MutableState<Track>? {
        return relevantTracks[spotifyTrackId]
    }

    fun removeFromRelevantTracks(spotifyTrackId: String) {
        relevantTracks.remove(spotifyTrackId)
    }


    protected fun internalAddTrackToVoteList(track: Track, trackListType: TrackListType) {
        // just an error catcher, should not happen
        if (isTrackInVoteList(track)) {
            Log.w("VOTELIST", "Track already inside")
            return
        }
        if (!isTrackRelevant(track.spotifyId)) {
            addRelevantTrack(track)
        }
        val trackUiInstance = TrackUiInstance(getRelevantTrack(track.spotifyId)!!, trackListType)
        voteList.add(trackUiInstance)
    }

    fun addUpvoteToTrack(spotifyTrackId: String){
        if(isTrackRelevant(spotifyTrackId)){
            getRelevantTrack(spotifyTrackId)!!.value.isUpvoted = true
        }
    }

    fun removeUpvoteFromTrack(spotifyTrackId: String){
        if(isTrackRelevant(spotifyTrackId)){
            getRelevantTrack(spotifyTrackId)!!.value.isUpvoted = false
        }
    }

    fun isTrackInVoteList(track: Track): Boolean {
        var isTrackInVoteList = false
        voteList.forEach {
            if (it.track.value.spotifyId == track.spotifyId) {
                isTrackInVoteList = true
            }
        }
        return isTrackInVoteList
    }

    fun isTrackInBlockedList(track: Track): Boolean {
        var isTrackInBlockedList = false
        blockedList.forEach {
            if (it.track.value.spotifyId == track.spotifyId) {
                isTrackInBlockedList = true
            }
        }
        return isTrackInBlockedList
    }

    abstract suspend fun searchTracks(searchInput: String)

    abstract fun addTrackToVoteList(track: Track)

}