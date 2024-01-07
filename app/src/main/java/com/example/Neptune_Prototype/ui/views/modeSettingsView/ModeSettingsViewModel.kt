package com.example.Neptune_Prototype.ui.views.modeSettingsView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.backend.HostBackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.user.Host
import com.example.Neptune_Prototype.ui.ViewsCollection

class ModeSettingsViewModel : ViewModel() {

    var playlistLinkInput by mutableStateOf("")

    var sliderPosition by mutableFloatStateOf(0f)

    var trackCooldown by mutableStateOf("")

    fun onPlaylistLinkInputChange(input: String) {
        playlistLinkInput = input
    }

    fun onSliderPositionChange(change: Float) {
        sliderPosition = change
        trackCooldown = (1000f * sliderPosition).toInt().toString()
    }

    fun onConfirmSettings(navController: NavController) {
        navController.navigate(ViewsCollection.CONTROL_VIEW.name)
        NeptuneApp.modelContainer.session = Session()
        NeptuneApp.modelContainer.backendConnector =
            HostBackendConnector(NeptuneApp.modelContainer.backendHttpClient)
        NeptuneApp.modelContainer.user = Host(
            NeptuneApp.modelContainer.session!!,
            NeptuneApp.modelContainer.backendConnector!! as HostBackendConnector,
            NeptuneApp.modelContainer.spotifyConnector
        )
    }

}