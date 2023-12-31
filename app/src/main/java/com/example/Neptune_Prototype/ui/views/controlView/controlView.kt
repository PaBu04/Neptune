package com.example.Neptune_Prototype.ui.views.controlView

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun ControlViewBody(navController: NavController){
    val controlViewModel = viewModel<ControlViewModel>(
        factory = viewModelFactory {
            ControlViewModel()
        }
    )
}

fun controlViewOnBack(navController: NavController) {
    // TODO navController.popBackStack(ViewsCollection.START_VIEW.name, inclusive = false, saveState = false)
}