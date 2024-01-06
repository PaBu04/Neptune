package com.example.Neptune_Prototype.data.model.backend

import io.ktor.client.HttpClient

class ParticipantBackendConnector(
    private val httpClient: HttpClient
) : BackendConnector(httpClient) {



    suspend fun participantJoinSession() {

    }


    suspend fun participantLeaveSession() {

    }
}