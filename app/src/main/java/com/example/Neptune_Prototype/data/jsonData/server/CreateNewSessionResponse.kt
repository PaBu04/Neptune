package com.example.Neptune_Prototype.data.jsonData.server

import kotlinx.serialization.Serializable

@Serializable
data class CreateNewSessionResponse(
    val sessionID: Int,
    val status: String,
    val timestamp: Int
)