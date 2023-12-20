package com.example.Neptune_Prototype.di

import android.content.Context
import androidx.room.Room
import com.example.Neptune_Prototype.data.repositories.SpotifyLinkingInfoRepo
import com.example.Neptune_Prototype.data.room.SpotifyLinkingInfoDatabase

class SpotifyLinkingInfoModule(
    private val context: Context
) {

    val spotifyLinkingInfoDatabase by lazy {
        Room.databaseBuilder(
            context,
            SpotifyLinkingInfoDatabase::class.java,
            "spotify_linking_info.db"
        ).build()
    }


    val spotifyLinkingInfoRepo by lazy {
        SpotifyLinkingInfoRepo(spotifyLinkingInfoDatabase.spotifyLinkingInfoDao)
    }

}