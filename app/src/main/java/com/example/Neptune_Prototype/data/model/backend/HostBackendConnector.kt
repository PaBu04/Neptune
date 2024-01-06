package com.example.Neptune_Prototype.data.model.backend

import io.ktor.client.HttpClient

class HostBackendConnector(
    private val httpClient: HttpClient
) : BackendConnector(httpClient) {




    suspend fun playedTrack() {

    }


    suspend fun setBlockTrack() {

    }

}