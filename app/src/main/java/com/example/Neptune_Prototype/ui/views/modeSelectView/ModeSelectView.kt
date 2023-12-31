package com.example.Neptune_Prototype.ui.views.modeSelectView

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun ModeSelectViewBody(navController: NavController){
    val modeSelectViewModel = viewModel<ModeSelectViewModel>(
        factory = viewModelFactory {
            ModeSelectViewModel()
        }
    )
}

fun modeSelectViewOnBack(navController: NavController) {
    // TODO navController.popBackStack(ViewsCollection.START_VIEW.name, inclusive = false, saveState = false)
}