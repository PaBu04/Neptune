package com.example.Neptune_Prototype.data.repositories

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import com.example.Neptune_Prototype.R
import com.example.Neptune_Prototype.data.room.SpotifyConnectionData
import com.example.Neptune_Prototype.data.room.SpotifyConnectionDataDao
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpStatement
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import org.json.JSONObject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class SpotifyConnector(
    private val spotifyConnectionDataDao: SpotifyConnectionDataDao,
    private val httpClient: HttpClient
) {

    private var accessToken = ""

    suspend fun hasLinkedEntry(): Boolean {
        return spotifyConnectionDataDao.entryCount() == 1
    }

    suspend fun isLinked(): Boolean {
        return spotifyConnectionDataDao.isLinked()
    }

    suspend fun updateLinked(isLinked: Boolean) {
        spotifyConnectionDataDao.upsert(SpotifyConnectionData(0, isLinked))
    }

    fun startLinking(context: Context) {
        val spotifyClientId = context.getString(R.string.spotify_client_id)
        val url = "https://accounts.spotify.com/authorize?client_id=" +
                spotifyClientId +
                "&response_type=code&redirect_uri=" +
                "oauth://neptune-spotify-callback" +
                "&scope=user-modify-playback-state"
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }


    @OptIn(ExperimentalEncodingApi::class)
    suspend fun getAccessToken(code: String) {
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
        Log.i("LINK", accessToken)
    }


    suspend fun getBestTrack(searchInput: String): String{
        val authResponse = httpClient.get<JSONObject>("https://api.spotify.com/v1/search") {
            header("Authorization", "Bearer " + accessToken)
            parameter("q", searchInput)
            parameter("type", "track")
            parameter("limit", "1")
        }
        return "Erg"
    }

}


@Serializable
data class AuthResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String
)

