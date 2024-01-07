package com.example.Neptune_Prototype.ui.views.controlView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import com.example.Neptune_Prototype.data.model.user.Host

class ControlViewModel(
    private val host: Host,
    private val voteList: SnapshotStateList<TrackUiInstance>,
    private val queueList: SnapshotStateList<TrackUiInstance>,
    private val isPlaybackPaused: MutableState<Boolean>
) : ViewModel() {

    fun onToggleUpvote(trackUiInstance: TrackUiInstance) {
        /*if (trackUiInstance.track.value.isUpvoted) {
            trackUiInstance.track.value.isUpvoted = false
            trackUiInstance.track.value.upvoteCount--
        } else {
            trackUiInstance.track.value.isUpvoted = true
            trackUiInstance.track.value.upvoteCount++
        }

        if (trackUiInstance.track.value.upvoteCount == 0) {
            session.removeFromVoteList(trackUiInstance)
        }*/
        //TODO
    }

    fun onAddToQueue(track: Track) {
        host.addTrackToQueueList(track)
    }

    fun onRemoveFromQueue(trackUiInstance: TrackUiInstance) {
        host.removeFromQueueList(trackUiInstance)
    }

    fun onPause() {
        isPlaybackPaused.value = !isPlaybackPaused.value
        if (isPlaybackPaused.value) {
            host.pausePlayback()
        } else {
            host.resumePlayback()
        }
    }

    fun onSkip() {
        host.skipTrack()
    }

    fun getPausedDescription(): String {
        if (isPlaybackPaused.value) {
            return "Weiter"
        } else {
            return "Pause"
        }
    }

    fun getVoteList(): SnapshotStateList<TrackUiInstance> {
        return voteList
    }

    fun getQueueList(): SnapshotStateList<TrackUiInstance> {
        return queueList
    }

}