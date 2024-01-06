package com.example.Neptune_Prototype.data.jsonData.trackSearch

import kotlinx.serialization.Serializable

@Serializable
data class TrackResponse(
    val tracks: Tracks
)