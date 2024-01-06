package com.example.Neptune_Prototype.di

import android.content.Context
import androidx.room.Room
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.user.User
import com.example.Neptune_Prototype.data.room.SpotifyConnectionDataDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

class ModelContainer (
    private val context: Context
){

    private val spotifyHttpClient by lazy {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json { explicitNulls = false })
            }
        }
    }

    private val spotifyConnectionDataDatabase by lazy {
        Room.databaseBuilder(
            context,
            SpotifyConnectionDataDatabase::class.java,
            "spotify_linking_info.db"
        ).build()
    }


    val spotifyConnector by lazy {
        SpotifyConnector(spotifyConnectionDataDatabase.spotifyConnectionDataDao, spotifyHttpClient)
    }


    var user = User(spotifyConnector, context)


    var session: Session? = null

}