package com.example.Neptune_Prototype.ui.views.infoView

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun InfoViewBody(navController: NavController){
    val infoViewModel = viewModel<InfoViewModel>(
        factory = viewModelFactory {
            InfoViewModel()
        }
    )
}

fun infoViewOnBack(navController: NavController) {
    // TODO navController.popBackStack(ViewsCollection.START_VIEW.name, inclusive = false, saveState = false)
}