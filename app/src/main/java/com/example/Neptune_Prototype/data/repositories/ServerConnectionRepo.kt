package com.example.Neptune_Prototype.data.repositories

import io.ktor.client.HttpClient
import io.ktor.client.request.post

class ServerConnectionRepo(
    private val httpClient: HttpClient
) {
    suspend fun getServerText(): String {
        val res = httpClient.post<Boolean>("http://45.93.251.238:5000/test")
        return res.toString()
    }
}