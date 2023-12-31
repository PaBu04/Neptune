package com.example.Neptune_Prototype.ui.views.voteView

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.TopBarAndBody
import com.example.Neptune_Prototype.ui.commons.TrackListComp
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun VoteViewBody(navController: NavController){
    val voteViewModel = viewModel<VoteViewModel>(
        factory = viewModelFactory {
            VoteViewModel(NeptuneApp.serverConnectionModule.serverConnectionRepo)
        }
    )

    TrackListComp()
}

fun voteViewOnBack(navController: NavController) {
    navController.popBackStack(ViewsCollection.START_VIEW.name, inclusive = false, saveState = false)
}