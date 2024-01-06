package com.example.Neptune_Prototype.data.jsonData.spotifyLevel

import kotlinx.serialization.Serializable

@Serializable
data class SpotifyLevelResponse(
    val country: String,
    val display_name: String,
    val explicit_content: ExplicitContent,
    val external_urls: ExternalUrls,
    val followers: Followers,
    val href: String,
    val id: String,
    val images: List<Image>,
    val product: String,
    val type: String,
    val uri: String
)