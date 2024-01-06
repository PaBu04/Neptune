package com.example.Neptune_Prototype.data.model.track

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Track(
    val spotifyId: String,
    val trackName: String,
    val imageUrl: String,
    val genres: MutableList<String>,
    val artists: MutableList<String>
) {

    var isUpvoted by mutableStateOf(false)
    var upvoteCount by mutableIntStateOf(0)
    var hasCooldown by mutableStateOf(false)
    var isLocked by mutableStateOf(false)


    fun getArtistNames(): String {
        var artistNames = ""
        artists.forEach {
            artistNames += "$it, "
        }
        return artistNames.substring(0, artistNames.length - 2)
    }
}