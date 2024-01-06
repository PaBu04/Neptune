package com.example.Neptune_Prototype.data.jsonData.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String
)