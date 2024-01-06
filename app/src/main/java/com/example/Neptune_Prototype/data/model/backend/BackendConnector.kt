package com.example.Neptune_Prototype.data.model.backend

import io.ktor.client.HttpClient

abstract class BackendConnector(
    private val httpClient: HttpClient
) {

    suspend fun createNewSession() {

    }


    suspend fun deleteSession() {

    }


    suspend fun getUserSessionState() {

    }


    suspend fun getAllTrackData() {

    }


    suspend fun getStatistics() {

    }


    suspend fun isSessionOpen() {

    }


    suspend fun addTrackToSession() {

    }


    suspend fun addUpvoteToTrack() {

    }


    suspend fun removeUpvoteFromTrack() {

    }


    suspend fun getLockedTracks() {

    }


}