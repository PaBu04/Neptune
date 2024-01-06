package com.example.Neptune_Prototype.ui.views.modeSettingsView

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.session.HostSession
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModeSettingsViewBody(navController: NavController) {
    val modeSettingsViewModel = viewModel<ModeSettingsViewModel>(
        factory = viewModelFactory {
            ModeSettingsViewModel()
        }
    )

    Column {

        Text(text = "Standardplaylist", color = Color.White)
        TextField(
            value = modeSettingsViewModel.playlistLinkInput,
            onValueChange = { modeSettingsViewModel.onPlaylistLinkInputChange(it) })
        Text(text = "Track Cooldown", color = Color.White)
        Slider(
            value = modeSettingsViewModel.sliderPosition,
            onValueChange = { modeSettingsViewModel.onSliderPositionChange(it) })
        Text(text = modeSettingsViewModel.trackCooldown + " min", color = Color.White)
        StandardButton(onClick = { onConfirmSettings(navController) }, text = "Bestätigen")
    }

}

fun modeSettingsViewOnBack(navController: NavController) {
    navController.popBackStack()
}

fun onConfirmSettings(navController: NavController){
    navController.navigate(ViewsCollection.CONTROL_VIEW.name)
    NeptuneApp.modelContainer.session = HostSession()
}