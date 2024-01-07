package com.example.Neptune_Prototype.ui.views.voteView

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.Neptune_Prototype.data.model.ServerConnectionRepo
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance

class VoteViewModel(
    private val session: Session,
    private val voteList: SnapshotStateList<TrackUiInstance>
) : ViewModel() {


    fun onToggleUpvote(trackUiInstance: TrackUiInstance){
        if (trackUiInstance.track.value.isUpvoted) {
            trackUiInstance.track.value.isUpvoted = false
            trackUiInstance.track.value.upvoteCount--
        } else {
            trackUiInstance.track.value.isUpvoted = true
            trackUiInstance.track.value.upvoteCount++
        }

        if(trackUiInstance.track.value.upvoteCount == 0){
            voteList.remove(trackUiInstance)
        }
    }

    fun getVoteList(): SnapshotStateList<TrackUiInstance> {
        return voteList
    }


}