package com.example.Neptune_Prototype.ui.views.modeSettingsView

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun ModeSettingsViewBody(navController: NavController){
    val modeSettingsViewModel = viewModel<ModeSettingsViewModel>(
        factory = viewModelFactory {
            ModeSettingsViewModel()
        }
    )
}

fun modeSettingsViewOnBack(navController: NavController) {
    // TODO navController.popBackStack(ViewsCollection.START_VIEW.name, inclusive = false, saveState = false)
}