package com.example.Neptune_Prototype.ui.views.modeSettingsView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ModeSettingsViewModel : ViewModel() {

    var playlistLinkInput by mutableStateOf("")

    var sliderPosition by mutableFloatStateOf(0f)

    var trackCooldown by mutableStateOf("")

    fun onPlaylistLinkInputChange(input: String){
        playlistLinkInput = input
    }

    fun onSliderPositionChange(change: Float){
        sliderPosition = change
        trackCooldown = (1000f * sliderPosition).toInt().toString()
    }

}