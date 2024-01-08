package com.example.Neptune_Prototype.data.model.user

import androidx.compose.runtime.mutableStateOf
import com.example.Neptune_Prototype.data.model.backend.BackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.ui.commons.TrackListType

open class FullParticipant(
    private val session: Session,
    private val backendConnector: BackendConnector,
    private val spotifyConnector: SpotifyConnector
) : User(session, backendConnector) {

    override suspend fun searchTracks(searchInput: String) {
        if(searchInput == ""){
            return
        }
        val searchResultTrackList = spotifyConnector.searchTracks(searchInput)
        searchList.clear()
        searchResultTrackList.forEach {
            val mutableStateTrackToAdd =
                if (isTrackRelevant(it.spotifyId)) getRelevantTrack(it.spotifyId)!!
                else mutableStateOf(it)
            searchList.add(mutableStateTrackToAdd)
        }
    }

}