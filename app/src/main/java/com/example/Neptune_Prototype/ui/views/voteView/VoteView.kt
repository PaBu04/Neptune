package com.example.Neptune_Prototype.ui.views.voteView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.StandardButton
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

    Column{
        Box(modifier = Modifier.weight(7f)) {
            TrackListComp()
        }
        StandardButton(onClick = { onSearchTrack(navController) }, text = "Track suchen")
    }
}

fun onSearchTrack(navController: NavController){
    navController.navigate(ViewsCollection.SEARCH_VIEW.name)
}

fun voteViewOnBack(navController: NavController) {
    navController.popBackStack(ViewsCollection.START_VIEW.name, inclusive = false, saveState = false)
}