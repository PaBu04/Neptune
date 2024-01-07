package com.example.Neptune_Prototype.ui.views.startView

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.Neptune_Prototype.data.model.app.AppState
import com.example.Neptune_Prototype.data.model.app.SpotifyLevel
import com.example.Neptune_Prototype.data.model.user.User

class StartViewModel(
    private val appState: AppState,
    private val spotifyLevel: MutableState<SpotifyLevel>
) : ViewModel() {

    fun canCreateSession(): Boolean {
        return spotifyLevel.value == SpotifyLevel.PREMIUM
    }

    fun toggleLinkedToSpotify() {
        if (spotifyLevel.value == SpotifyLevel.FREE || spotifyLevel.value == SpotifyLevel.PREMIUM) {
            appState.unlinkFromSpotify()
        } else if (spotifyLevel.value == SpotifyLevel.UNLINKED) {
            appState.connectToSpotifyWithAuthorize()
        }
    }

    fun getSpotifyButtonText(): String {
        if (spotifyLevel.value == SpotifyLevel.FREE || spotifyLevel.value == SpotifyLevel.PREMIUM) {
            return "Von Spotify trennen"
        } else if (spotifyLevel.value == SpotifyLevel.UNLINKED) {
            return "Mit Spotify verknüpfen"
        } else {
            return ""
        }
    }


}