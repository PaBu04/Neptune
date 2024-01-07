package com.example.Neptune_Prototype.data.model.app

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.room.app.AppData
import com.example.Neptune_Prototype.data.room.app.AppDataDao
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionDataDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.MessageDigest


class AppState(
    private val spotifyConnector: SpotifyConnector,
    private val appDataDao: AppDataDao,
    private val context: Context
) {

    var deviceId = ""

    var spotifyLevel = mutableStateOf(SpotifyLevel.UNDETERMINED)
        private set


    init {
        GlobalScope.launch {
            val isLinkedToSpotify = spotifyConnector.isLinkedToSpotify()
            if (isLinkedToSpotify) {
                val refreshTokenUsed = spotifyConnector.connectToSpotify(context)
                if (refreshTokenUsed) {
                    setSpotifyLevel()
                }
            }
        }
        GlobalScope.launch {
            setDeviceId()
        }
    }

    fun connectToSpotifyWithAuthorize() {
        GlobalScope.launch {
            spotifyConnector.connectToSpotifyWithAuthorize(context)
        }
    }

    fun unlinkFromSpotify() {
        GlobalScope.launch {
            spotifyConnector.unlinkFromSpotify()
        }
        spotifyLevel.value = SpotifyLevel.UNLINKED
    }

    suspend fun setSpotifyLevel() {
        val spotifyLevelString = spotifyConnector.getSpotifyLevel()
        if (spotifyLevelString == "premium") {
            spotifyLevel.value = SpotifyLevel.PREMIUM
        } else if (spotifyLevelString == "free" || spotifyLevelString == "open") {
            spotifyLevel.value = SpotifyLevel.FREE
        }
        Log.i("LEVEL", spotifyLevel.toString())
    }


    private suspend fun setDeviceId() {
        if (appDataDao.entryCount() == 1) {
             deviceId = appDataDao.getDeviceId()
        } else {
            deviceId = generateDeviceId()
            appDataDao.upsert(AppData(0, deviceId))
        }
    }

    private fun generateDeviceId(): String {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val macAddress = wifiManager.connectionInfo.macAddress
        val randomAddition = (0..Int.MAX_VALUE).random().toString()
        return generateSHA256(macAddress + randomAddition)
    }

    private fun generateSHA256(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hexString = StringBuffer()

        for (byte in digest) {
            val hex = Integer.toHexString(0xff and byte.toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }


}