package com.example.Neptune_Prototype.data.model.user

import com.example.Neptune_Prototype.data.model.backend.BackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackListType

class RestrictedParticipant(
    private val session: Session,
    private val backendConnector: BackendConnector
) : User(session, backendConnector) {

    override suspend fun searchTracks(searchInput: String) {
        searchList.clear()
        //TODO
    }

    override fun addTrackToVoteList(track: Track) {
        internalAddTrackToVoteList(track, TrackListType.PARTICIPANT_VOTE)
    }

}