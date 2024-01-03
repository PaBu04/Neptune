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
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpStatement
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.serialization.serializer
import org.json.JSONObject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class SpotifyConnector(
    private val spotifyConnectionDataDao: SpotifyConnectionDataDao,
    private val httpClient: HttpClient
) {

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
    suspend fun getAccessToken(code: String): String {
        val toEncode = "12176f1d92634db682ee1db0a6d8aa3b:93b84241520f436889410a326855ec2c"
        val encodedCredentials = "Basic " + Base64.encode(toEncode.toByteArray())
        val res = httpClient.post<HttpStatement>("https://accounts.spotify.com/api/token") {
            //header("Content-Type", "application/x-www-form-urlencoded")
            header(
                "Authorization",
                encodedCredentials
            )
            //parameter("grant_type", "authorization_code")
            //parameter("code", code)
            //parameter("redirect_uri", "oauth://neptune-spotify-callback")
            body = FormDataContent(
                Parameters.build {
                    append("grant_type", "authorization_code")
                    append("code", code)
                    append("redirect_uri", "oauth://neptune-spotify-callback")
                }
            )
        }
        serializer<>()
        Log.w("JSON", res.toString())
        return ""
    }
}

