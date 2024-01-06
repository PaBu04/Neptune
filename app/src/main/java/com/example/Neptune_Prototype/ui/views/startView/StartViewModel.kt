package com.example.Neptune_Prototype.ui.views.startView

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Neptune_Prototype.data.model.spotify.SpotifyConnector
import com.example.Neptune_Prototype.data.model.user.SpotifyLevel
import com.example.Neptune_Prototype.data.model.user.User
import kotlinx.coroutines.launch

class StartViewModel(
    private val user: User
) : ViewModel() {

    fun canCreateSession(): Boolean {
        return user.spotifyLevel == SpotifyLevel.PREMIUM
    }

    fun toggleLinkedToSpotify() {
        if (user.spotifyLevel == SpotifyLevel.FREE || user.spotifyLevel == SpotifyLevel.PREMIUM) {
            user.unlinkFromSpotify()
        } else {
            user.connectToSpotifyWithAuthorize()
        }
    }

    fun getSpotifyButtonText(): String {
        if (user.spotifyLevel == SpotifyLevel.FREE || user.spotifyLevel == SpotifyLevel.PREMIUM) {
            return "Von Spotify trennen"
        } else {
            return "Mit Spotify verknüpfen"
        }
    }


}