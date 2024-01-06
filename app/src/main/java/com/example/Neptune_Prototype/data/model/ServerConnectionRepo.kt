package com.example.Neptune_Prototype.data.model

import io.ktor.client.HttpClient

class ServerConnectionRepo(
    private val httpClient: HttpClient
) {
    suspend fun getServerText(): String {
        //val res = httpClient.post<Boolean>("http://45.93.251.238:5000/test")
        //return res.toString()
        return ""
    }
}