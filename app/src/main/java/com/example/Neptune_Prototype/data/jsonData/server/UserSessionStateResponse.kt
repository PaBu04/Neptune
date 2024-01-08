package com.example.Neptune_Prototype.data.jsonData.server

import kotlinx.serialization.Serializable

@Serializable
data class UserSessionStateResponse(
    val status: String,
    val userSessionState: String
)