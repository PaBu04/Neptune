package com.example.Neptune_Prototype.data.jsonData.trackSearch

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)