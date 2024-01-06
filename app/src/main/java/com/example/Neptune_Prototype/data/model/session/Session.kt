package com.example.Neptune_Prototype.data.model.session

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import java.util.HashMap

open class Session() {

    val sessionId = ""

    private val voteList by mutableStateOf(mutableStateListOf<TrackUiInstance>())

    private val relevantTracks = HashMap<String, MutableState<Track>>()



    fun addRelevantTrack(track: Track){
        relevantTracks[track.trackName] = mutableStateOf(track)
    }

    fun isTrackRelevant(spotifyTrackId: String): Boolean {
        return relevantTracks.containsKey(spotifyTrackId)
    }

    fun getRelevantTrack(spotifyTrackId: String): MutableState<Track>? {
        return relevantTracks[spotifyTrackId]
    }

    fun removeFromRelevantTracks(spotifyTrackId: String){
        relevantTracks.remove(spotifyTrackId)
    }


    fun addUpvoteToTrack(spotifyTrackId: String){
        if(isTrackRelevant(spotifyTrackId)){
            getRelevantTrack(spotifyTrackId)?.value?.isUpvoted = true
        }
    }

    fun removeUpvoteFromTrack(spotifyTrackId: String){
        if(isTrackRelevant(spotifyTrackId)){
            getRelevantTrack(spotifyTrackId)?.value?.isUpvoted = false
        }
    }


    fun addToVoteList(trackUiInstance: TrackUiInstance) {
        voteList.add(trackUiInstance)
    }

    fun removeFromVoteList(trackUiInstance: TrackUiInstance) {
        voteList.remove(trackUiInstance)
    }

    @JvmName("getterForVoteList")
    fun getVoteList(): SnapshotStateList<TrackUiInstance> {
        return voteList
    }

}