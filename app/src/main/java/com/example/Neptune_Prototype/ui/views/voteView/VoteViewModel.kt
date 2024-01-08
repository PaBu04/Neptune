package com.example.Neptune_Prototype.ui.views.voteView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.Neptune_Prototype.data.model.ServerConnectionRepo
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.user.User

class VoteViewModel(
    private val user: User
) : ViewModel() {


    fun onToggleUpvote(track: Track){
        if (track.isUpvoted) {
            user.removeUpvoteFromTrack(track)
        } else {
            user.addUpvoteToTrack(track)
        }
    }

    fun updateVoteList(){
        user.updateTrackData()
    }

    fun getVoteList(): SnapshotStateList<MutableState<Track>> {
        return user.voteList
    }


}