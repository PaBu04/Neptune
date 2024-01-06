package com.example.Neptune_Prototype.ui.views.controlView

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.Neptune_Prototype.data.model.session.HostSession
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackListType
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance

class ControlViewModel(
    private val spotifyConnector: SpotifyConnector,
    private val session: HostSession
) : ViewModel() {

    var voteList = session.getVoteList()

    var queueList = session.getQueueList()

    private var isPaused = session.isPaused


    fun onToggleUpvote(trackUiInstance: TrackUiInstance) {
        if (trackUiInstance.track.value.isUpvoted) {
            trackUiInstance.track.value.isUpvoted = false
            trackUiInstance.track.value.upvoteCount--
        } else {
            trackUiInstance.track.value.isUpvoted = true
            trackUiInstance.track.value.upvoteCount++
        }

        if (trackUiInstance.track.value.upvoteCount == 0) {
            session.removeFromVoteList(trackUiInstance)
        }
    }

    fun onAddToQueue(track: Track) {
        session.addToQueueList(TrackUiInstance(mutableStateOf(track), TrackListType.HOST_QUEUE))
    }

    fun onRemoveFromQueue(trackUiInstance: TrackUiInstance) {
        session.removeFromQueueList(trackUiInstance)
    }

    fun onPause() {
        isPaused = !isPaused
        if (isPaused) {
            session.pausePlayback()
        } else {
            session.resumePlayback()
        }
    }

    fun onSkip() {
        session.skipTrack()
    }

    fun getPausedDescription(): String {
        if (isPaused) {
            return "Weiter"
        } else {
            return "Pause"
        }
    }

}