package com.example.Neptune_Prototype.data.jsonData.spotifyLevel

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val height: Int,
    val url: String,
    val width: Int
)