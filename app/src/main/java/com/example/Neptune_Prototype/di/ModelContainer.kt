package com.example.Neptune_Prototype.di

import android.content.Context
import androidx.room.Room
import com.android.volley.toolbox.Volley
import com.example.Neptune_Prototype.data.model.app.AppState
import com.example.Neptune_Prototype.data.model.backend.BackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.user.User
import com.example.Neptune_Prototype.data.room.app.AppDataDatabase
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionDataDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

class ModelContainer (
    private val context: Context
){

    private val appDataDatabase by lazy {
        Room.databaseBuilder(
            context,
            AppDataDatabase::class.java,
            "app_data.db"
        ).build()
    }

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
            "spotify_connection_data.db"
        ).build()
    }


    val spotifyConnector by lazy {
        SpotifyConnector(spotifyConnectionDataDatabase.spotifyConnectionDataDao, spotifyHttpClient)
    }


    val backendVolleyQueue by lazy {
        Volley.newRequestQueue(context)
    }


    var appState = AppState(spotifyConnector, appDataDatabase.appDataDao, context)

    var user: User? = null

    var session: Session? = null

    var backendConnector: BackendConnector? = null
    //TODO session und backendconnector in den user rein
}