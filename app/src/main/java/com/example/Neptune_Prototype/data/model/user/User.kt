package com.example.Neptune_Prototype.data.model.user

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class User(
    val spotifyConnector: SpotifyConnector,
    private val context: Context
) {

    var userId = ""

    var spotifyLevel by mutableStateOf(SpotifyLevel.UNLINKED)
        private set

    var userType by mutableStateOf(UserType.UNDEFINED)
        private set


    init {
        GlobalScope.launch {
            val isLinkedToSpotify = spotifyConnector.isLinkedToSpotify()
            if (isLinkedToSpotify) {
                val refreshTokenUsed = spotifyConnector.connectToSpotify(context)
                if(refreshTokenUsed){
                    setSpotifyLevel()
                }
            }
        }
    }

    fun connectToSpotifyWithAuthorize() {
        GlobalScope.launch {
            spotifyConnector.connectToSpotifyWithAuthorize(context)
        }
    }

    fun unlinkFromSpotify(){
        GlobalScope.launch {
            spotifyConnector.unlinkFromSpotify()
        }
        spotifyLevel = SpotifyLevel.UNLINKED
    }

    suspend fun setSpotifyLevel(){
        val spotifyLevelString = spotifyConnector.getSpotifyLevel()
        if (spotifyLevelString == "premium") {
            spotifyLevel = SpotifyLevel.PREMIUM
        } else if (spotifyLevelString == "free" || spotifyLevelString == "open") {
            spotifyLevel = SpotifyLevel.FREE
        }
        Log.i("LEVEL", spotifyLevel.toString())
    }


}