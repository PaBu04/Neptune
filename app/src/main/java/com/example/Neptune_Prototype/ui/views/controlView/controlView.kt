package com.example.Neptune_Prototype.ui.views.controlView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.session.HostSession
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.commons.TrackListComp
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun ControlViewBody(navController: NavController) {
    val controlViewModel = viewModel<ControlViewModel>(
        factory = viewModelFactory {
            ControlViewModel(NeptuneApp.modelContainer.spotifyConnector,
                NeptuneApp.modelContainer.session as HostSession
            )
        }
    )

    Column {

        Text(text = "Queue", color = Color.White)
        Box(modifier = Modifier.weight(7f)) {
            TrackListComp(
                tracks = controlViewModel.queueList,
                onToggleUpvote = { controlViewModel.onToggleUpvote(it) },
                onAddToQueue = {},
                onRemoveFromQueue = {controlViewModel.onRemoveFromQueue(it) } )
        }

        Text(text = "Upvote Liste", color = Color.White)
        Box(modifier = Modifier.weight(7f)) {
            TrackListComp(
                tracks = controlViewModel.voteList,
                onToggleUpvote = { controlViewModel.onToggleUpvote(it) },
                onAddToQueue = { controlViewModel.onAddToQueue(it) },
                onRemoveFromQueue = {} )
        }
        StandardButton(onClick = { controlViewModel.onPause() }, text = controlViewModel.getPausedDescription())
        StandardButton(onClick = { controlViewModel.onSkip() }, text = "Überspringen")
        StandardButton(onClick = { onHostSearchTrack(navController) }, text = "Track suchen")
    }


}

fun controlViewOnBack(navController: NavController) {
    navController.popBackStack(
        ViewsCollection.START_VIEW.name,
        inclusive = false,
        saveState = false
    )
}


fun onHostSearchTrack(navController: NavController) {
    navController.navigate(ViewsCollection.SEARCH_VIEW.name)
}