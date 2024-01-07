package com.example.Neptune_Prototype.ui.views.searchView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Neptune_Prototype.data.model.app.SpotifyLevel
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackListType
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import com.example.Neptune_Prototype.data.model.user.User
import kotlinx.coroutines.launch

class SearchViewModel(
    private val user: User,
    private val searchList: SnapshotStateList<TrackUiInstance>
) : ViewModel() {

    var trackSearchInput by mutableStateOf("")
        private set

    fun onSearchInputChange(input: String) {
        trackSearchInput = input
        viewModelScope.launch {
            user.searchTracks(input)
        }
    }

    fun onToggleUpvote(trackUiInstance: TrackUiInstance) {
        if (trackUiInstance.track.value.isUpvoted) {
            trackUiInstance.track.value.isUpvoted = false
            trackUiInstance.track.value.upvoteCount--
        } else {
            trackUiInstance.track.value.isUpvoted = true
            trackUiInstance.track.value.upvoteCount++
            user.addTrackToVoteList(trackUiInstance.track.value)
        }
    }

    fun getSearchList(): SnapshotStateList<TrackUiInstance> {
        return searchList
    }


}