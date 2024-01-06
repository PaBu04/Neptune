package com.example.Neptune_Prototype.data.jsonData.spotifyLevel

import kotlinx.serialization.Serializable

@Serializable
data class ExplicitContent(
    val filter_enabled: Boolean,
    val filter_locked: Boolean
)