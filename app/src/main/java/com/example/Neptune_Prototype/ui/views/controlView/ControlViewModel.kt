package com.example.Neptune_Prototype.ui.views.controlView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.user.Host
import com.example.Neptune_Prototype.data.model.user.PlaybackState

class ControlViewModel(
    private val host: Host
) : ViewModel() {

    fun onToggleUpvote(track: Track) {
        if (track.isUpvoted) {
            host.removeUpvoteFromTrack(track)
        } else {
            host.addUpvoteToTrack(track)
        }
    }

    fun onAddToQueue(track: Track) {
        host.addTrackToQueueList(track)
    }

    fun onRemoveFromQueue(index: Int) {
        host.removeFromQueueList(index)
    }

    fun onTogglePause() {
        if (getPlayBackState().value == PlaybackState.PLAYING) {
            host.pausePlayback()
        } else if (getPlayBackState().value == PlaybackState.PAUSED) {
            host.resumePlayback()
        }
    }

    fun onSkip() {
        host.skipTrack()
    }

    fun getPausedDescription(): String {
        if ((getPlayBackState().value == PlaybackState.PAUSED)) {
            return "Weiter"
        } else {
            return "Pause"
        }
    }

    fun updateVoteList() {
        host.updateTrackData()
    }

    fun getPlayBackState(): MutableState<PlaybackState> {
        return host.playbackState
    }

    fun getVoteList(): SnapshotStateList<MutableState<Track>> {
        return host.voteList
    }

    fun getQueueList(): SnapshotStateList<MutableState<Track>> {
        return host.queueList
    }

}