package com.example.Neptune_Prototype.data.jsonData.spotifyLevel

import kotlinx.serialization.Serializable

@Serializable
data class Followers(
    val href: String?,
    val total: Int
)