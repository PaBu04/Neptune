package com.example.Neptune_Prototype.data.jsonData.auth

import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val scope: String
)