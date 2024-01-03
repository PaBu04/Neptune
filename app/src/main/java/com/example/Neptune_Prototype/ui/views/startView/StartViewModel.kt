package com.example.Neptune_Prototype.ui.views.startView

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Neptune_Prototype.data.repositories.SpotifyConnector
import kotlinx.coroutines.launch

class StartViewModel(
    private val spotifyConnector: SpotifyConnector,
    private val context: Context
) : ViewModel() {

    var isLinkedToSpotify by mutableStateOf(false)
        private set
    var spotifyButtonText by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            if (spotifyConnector.hasLinkedEntry()) {
                isLinkedToSpotify = spotifyConnector.isLinked()
            } else {
                spotifyConnector.updateLinked(isLinkedToSpotify)
            }
            updateSpotifyButtonText()
        }
    }

    fun toggleLinkedToSpotify() {
        if(!isLinkedToSpotify){
            spotifyConnector.startLinking(context)
        }
        isLinkedToSpotify = !isLinkedToSpotify
        updateSpotifyButtonText()
        viewModelScope.launch {
            spotifyConnector.updateLinked(isLinkedToSpotify)
        }
    }

    private fun updateSpotifyButtonText(){
        if (isLinkedToSpotify) {
            spotifyButtonText = "Von Spotify trennen"
        } else {
            spotifyButtonText = "Mit Spotify verknüpfen"
        }
    }


}