package com.example.Neptune_Prototype.data.model.spotify

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import com.example.Neptune_Prototype.R
import com.example.Neptune_Prototype.data.jsonData.auth.AuthResponse
import com.example.Neptune_Prototype.data.jsonData.auth.RefreshResponse
import com.example.Neptune_Prototype.data.jsonData.spotifyLevel.SpotifyLevelResponse
import com.example.Neptune_Prototype.data.jsonData.trackSearch.Item
import com.example.Neptune_Prototype.data.jsonData.trackSearch.TrackResponse
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionData
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionDataDao
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

open class SpotifyConnector(
    private val spotifyConnectionDataDao: SpotifyConnectionDataDao,
    private val httpClient: HttpClient
) {

    private var accessToken = ""
    private var refreshToken = ""

    suspend fun isLinkedToSpotify(): Boolean {
        if (hasLinkedEntry()) {
            return isLinked()
        } else {
            updateLinked(false)
            return false
        }
    }

    // returns if a refresh token could be used
    suspend fun connectToSpotify(context: Context): Boolean {
        if (isRefreshTokenAvailable()) {
            getAccessTokenFromRefreshToken()
            return true
        } else {
            authorizeConnectionToSpotify(context)
            return false
        }
    }

    suspend fun unlinkFromSpotify() {
        updateLinked(false)
    }

    suspend fun connectToSpotifyWithAuthorize(context: Context) {
        authorizeConnectionToSpotify(context)
    }

    private fun authorizeConnectionToSpotify(context: Context) {
        val spotifyClientId = context.getString(R.string.spotify_client_id)
        val url = "https://accounts.spotify.com/authorize?client_id=" +
                spotifyClientId +
                "&response_type=code&redirect_uri=" +
                "oauth://neptune-spotify-callback" +
                "&scope=user-modify-playback-state user-read-private"
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }


    @OptIn(ExperimentalEncodingApi::class)
    suspend fun exchangeCodeToAccessToken(code: String) {
        val toEncode = "12176f1d92634db682ee1db0a6d8aa3b:93b84241520f436889410a326855ec2c"
        val encodedCredentials = "Basic " + Base64.encode(toEncode.toByteArray())
        val authResponse = httpClient.post<AuthResponse>("https://accounts.spotify.com/api/token") {
            header("Authorization", encodedCredentials)
            body = FormDataContent(
                Parameters.build {
                    append("grant_type", "authorization_code")
                    append("code", code)
                    append("redirect_uri", "oauth://neptune-spotify-callback")
                }
            )
        }
        accessToken = authResponse.access_token
        refreshToken = authResponse.refresh_token
        updateLinked(true, refreshToken)
        Log.i("LINK", accessToken)
    }

    @OptIn(ExperimentalEncodingApi::class)
    private suspend fun getAccessTokenFromRefreshToken() {
        refreshToken = spotifyConnectionDataDao.getRefreshToken()

        val toEncode = "12176f1d92634db682ee1db0a6d8aa3b:93b84241520f436889410a326855ec2c"
        val encodedCredentials = "Basic " + Base64.encode(toEncode.toByteArray())
        val refreshResponse =
            httpClient.post<RefreshResponse>("https://accounts.spotify.com/api/token") {
                header("Authorization", encodedCredentials)
                body = FormDataContent(
                    Parameters.build {
                        append("grant_type", "refresh_token")
                        append("refresh_token", refreshToken)
                    }
                )
            }
        accessToken = refreshResponse.access_token
        Log.i("LINK", accessToken)
    }

    suspend fun getSpotifyLevel(): String {
        val spotifyLevelResponse = httpClient.get<SpotifyLevelResponse>("https://api.spotify.com/v1/me") {
            header("Authorization", "Bearer " + accessToken)
        }
        return spotifyLevelResponse.product
    }

    suspend fun searchTracks(searchInput: String): List<Track> {
        val trackResponse = httpClient.get<TrackResponse>("https://api.spotify.com/v1/search") {
            header("Authorization", "Bearer " + accessToken)
            parameter("q", searchInput)
            parameter("type", "track")
            parameter("limit", "20")
        }
        val trackList = mutableListOf<Track>()
        trackResponse.tracks.items.forEach {
            trackList.add(
                Track(
                    it.id,
                    it.name,
                    it.album.images[0].url,
                    mutableListOf("genre"),
                    getArtistsFromJSONItem(it)
                )
            )
        }
        return trackList
    }


    suspend fun playTrack(spotifyTrackId: String) {
        val jsonString = "{\"uris\":[\"spotify:track:$spotifyTrackId\"], \"position_ms\": \"0\"}"
        httpClient.put<HttpResponse>("https://api.spotify.com/v1/me/player/play") {
            header("Authorization", "Bearer " + accessToken)
            contentType(ContentType.Application.Json)
            body = Json.parseToJsonElement(jsonString)
        }
    }

    suspend fun pause() {
        httpClient.put<HttpResponse>("https://api.spotify.com/v1/me/player/pause") {
            header("Authorization", "Bearer " + accessToken)
        }
    }

    suspend fun resume() {
        httpClient.put<HttpResponse>("https://api.spotify.com/v1/me/player/play") {
            header("Authorization", "Bearer " + accessToken)
        }
    }


    private fun getArtistsFromJSONItem(item: Item): MutableList<String> {
        val artistNamesList = mutableListOf<String>()
        item.artists.forEach {
            artistNamesList.add(it.name)
        }
        return artistNamesList
    }


    private suspend fun hasLinkedEntry(): Boolean {
        return spotifyConnectionDataDao.entryCount() == 1
    }

    private suspend fun isLinked(): Boolean {
        return spotifyConnectionDataDao.isLinked()
    }

    private suspend fun updateLinked(isLinked: Boolean, refreshToken: String = "") {
        spotifyConnectionDataDao.upsert(SpotifyConnectionData(0, isLinked, refreshToken))
    }

    private suspend fun isRefreshTokenAvailable(): Boolean {
        refreshToken = spotifyConnectionDataDao.getRefreshToken()
        return refreshToken != ""
    }

}





