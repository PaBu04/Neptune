package com.example.Neptune_Prototype.di

import android.content.Context
import androidx.room.Room
import com.example.Neptune_Prototype.data.repositories.SpotifyConnector
import com.example.Neptune_Prototype.data.room.SpotifyConnectionDataDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

class SpotifyConnectionDataModule(
    private val context: Context
) {

    val httpClient by lazy{
        HttpClient(Android){
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }
    }

    val spotifyConnectionDataDatabase by lazy {
        Room.databaseBuilder(
            context,
            SpotifyConnectionDataDatabase::class.java,
            "spotify_linking_info.db"
        ).build()
    }


    val spotifyConnector by lazy {
        SpotifyConnector(spotifyConnectionDataDatabase.spotifyConnectionDataDao, httpClient)
    }

}