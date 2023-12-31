package com.example.Neptune_Prototype.ui.views.statsView

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun StatsViewBody(navController: NavController){
    val statsViewModel = viewModel<StatsViewModel>(
        factory = viewModelFactory {
            StatsViewModel()
        }
    )
}

fun statsViewOnBack(navController: NavController) {
    // TODO navController.popBackStack(ViewsCollection.START_VIEW.name, inclusive = false, saveState = false)
}