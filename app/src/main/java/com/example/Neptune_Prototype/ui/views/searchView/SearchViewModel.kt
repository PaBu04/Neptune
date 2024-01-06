package com.example.Neptune_Prototype.ui.views.searchView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackListType
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import kotlinx.coroutines.launch

class SearchViewModel(
    private val spotifyConnector: SpotifyConnector,
    private val session: Session
) : ViewModel() {

    var trackSearchInput by mutableStateOf("")
        private set

    var trackList by mutableStateOf(mutableStateListOf<TrackUiInstance>())
        private set

    fun onSearchInputChange(input: String) {
        trackSearchInput = input
        viewModelScope.launch {
            if (trackSearchInput != "") {
                buildUiTrackList(spotifyConnector.searchTracks(trackSearchInput))
            }
        }
    }

    fun onToggleUpvote(trackUiInstance: TrackUiInstance) {
        if (trackUiInstance.track.value.isUpvoted) {
            trackUiInstance.track.value.isUpvoted = false
            trackUiInstance.track.value.upvoteCount--
        } else {
            trackUiInstance.track.value.isUpvoted = true
            trackUiInstance.track.value.upvoteCount++
            val trackToAdd =
                Track(trackUiInstance.track.value.spotifyId, trackUiInstance.track.value.trackName, trackUiInstance.track.value.imageUrl, trackUiInstance.track.value.genres, trackUiInstance.track.value.artists)
            trackToAdd.upvoteCount = trackUiInstance.track.value.upvoteCount
            trackToAdd.isUpvoted = true
            val trackUiInstanceToAdd = TrackUiInstance(mutableStateOf(trackToAdd), TrackListType.HOST_VOTE)
            session.addToVoteList(trackUiInstanceToAdd)
        }
    }


    private fun buildUiTrackList(trackListResource: MutableList<Track>) {
        trackList.clear()
        trackListResource.forEach {
            trackList.add(TrackUiInstance(mutableStateOf(it), TrackListType.HOST_SEARCH))
        }
    }

}