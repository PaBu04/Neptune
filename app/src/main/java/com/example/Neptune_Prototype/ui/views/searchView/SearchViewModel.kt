package com.example.Neptune_Prototype.ui.views.searchView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.user.FullParticipant
import com.example.Neptune_Prototype.data.model.user.Host
import com.example.Neptune_Prototype.data.model.user.RestrictedParticipant
import com.example.Neptune_Prototype.data.model.user.User
import com.example.Neptune_Prototype.ui.commons.TrackListType
import kotlinx.coroutines.launch

class SearchViewModel(
    private val user: User
) : ViewModel() {

    var trackSearchInput by mutableStateOf("")
        private set

    var expandedDropdownIndex by mutableStateOf(-1)
        private set

    fun onSearchInputChange(input: String) {
        trackSearchInput = input
        viewModelScope.launch {
            user.searchTracks(input)
        }
    }

    fun onToggleUpvote(track: Track) {
        if (track.isUpvoted) {
            user.removeUpvoteFromTrack(track)
        } else {
            user.addUpvoteToTrack(track)
        }
    }

    fun getSearchTrackListType(): TrackListType {
        if (user is Host) {
            return TrackListType.HOST_SEARCH
        } else {
            return TrackListType.PARTICIPANT_SEARCH
        }
    }

    fun onToggleDropdown(index: Int) {
        if (expandedDropdownIndex == -1) {
            expandedDropdownIndex = index
        } else {
            expandedDropdownIndex = -1
        }
    }

    fun isDropdownExpanded(index: Int): Boolean {
        return expandedDropdownIndex == index
    }

    fun getSearchList(): SnapshotStateList<MutableState<Track>> {
        return user.searchList
    }


}