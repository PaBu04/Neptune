package com.example.Neptune_Prototype.ui.views.startView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Neptune_Prototype.data.repositories.SpotifyLinkingInfoRepo
import kotlinx.coroutines.launch

class StartViewModel(
    private val spotifyLinkingInfoRepo: SpotifyLinkingInfoRepo
) : ViewModel() {

    var isLinkedToSpotify by mutableStateOf(false)
        private set
    var spotifyButtonText by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            if (spotifyLinkingInfoRepo.hasLinkedEntry()) {
                isLinkedToSpotify = spotifyLinkingInfoRepo.isLinked()
            } else {
                spotifyLinkingInfoRepo.updateLinked(isLinkedToSpotify)
            }
            updateSpotifyButtonText()
        }
    }

    fun toggleLinkedToSpotify() {
        isLinkedToSpotify = !isLinkedToSpotify
        updateSpotifyButtonText()
        viewModelScope.launch {
            spotifyLinkingInfoRepo.updateLinked(isLinkedToSpotify)
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