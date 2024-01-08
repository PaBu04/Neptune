package com.example.Neptune_Prototype.ui.views.startView

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.Neptune_Prototype.data.model.app.AppState
import com.example.Neptune_Prototype.data.model.app.SpotifyLevel

class StartViewModel(
    private val appState: AppState
) : ViewModel() {

    fun createSessionPossible(): Boolean {
        return getSpotifyLevel().value == SpotifyLevel.PREMIUM
    }

    fun toggleLinkedToSpotify() {
        if (getSpotifyLevel().value == SpotifyLevel.FREE || getSpotifyLevel().value == SpotifyLevel.PREMIUM) {
            appState.unlinkFromSpotify()
        } else if (getSpotifyLevel().value == SpotifyLevel.UNLINKED) {
            appState.connectToSpotifyWithAuthorize()
        }
    }

    fun getSpotifyButtonText(): String {
        if (getSpotifyLevel().value == SpotifyLevel.FREE || getSpotifyLevel().value == SpotifyLevel.PREMIUM) {
            return "Von Spotify trennen"
        } else if (getSpotifyLevel().value == SpotifyLevel.UNLINKED) {
            return "Mit Spotify verknüpfen"
        } else {
            return ""
        }
    }


    fun getSpotifyLevel(): MutableState<SpotifyLevel> {
        return appState.spotifyLevel
    }


}