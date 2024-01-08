package com.example.Neptune_Prototype.ui.views.controlView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.user.Host
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.commons.TrackListComposable
import com.example.Neptune_Prototype.ui.commons.TrackListType
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory
import kotlinx.coroutines.delay


@Composable
fun ControlViewBody(navController: NavController) {
    val controlViewModel = viewModel<ControlViewModel>(
        factory = viewModelFactory {
            ControlViewModel(NeptuneApp.modelContainer.user as Host
            )
        }
    )

    Column {

        Text(text = "Queue", color = Color.White)
        Box(modifier = Modifier.weight(7f)) {
            TrackListComposable(
                tracks = controlViewModel.getQueueList(),
                trackListType = TrackListType.HOST_QUEUE,
                onToggleUpvote = { controlViewModel.onToggleUpvote(it) },
                onRemoveFromQueue = {controlViewModel.onRemoveFromQueue(it) })
        }

        Text(text = "Upvote Liste", color = Color.White)
        Box(modifier = Modifier.weight(7f)) {
            TrackListComposable(
                tracks = controlViewModel.getVoteList(),
                trackListType = TrackListType.HOST_VOTE,
                onToggleUpvote = { controlViewModel.onToggleUpvote(it) },
                onAddToQueue = { controlViewModel.onAddToQueue(it) } )
        }
        StandardButton(onClick = { controlViewModel.onTogglePause() }, text = controlViewModel.getPausedDescription())
        StandardButton(onClick = { controlViewModel.onSkip() }, text = "Überspringen")
        StandardButton(onClick = { onHostSearchTrack(navController) }, text = "Track suchen")
    }

    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            controlViewModel.updateVoteList()
            delay(5000)
        }
    })

}

fun controlViewOnBack(navController: NavController) {
    navController.popBackStack(
        ViewsCollection.START_VIEW.name,
        inclusive = false,
        saveState = false
    )

    (NeptuneApp.modelContainer.user as Host).deleteSession()
}


fun onHostSearchTrack(navController: NavController) {
    navController.navigate(ViewsCollection.SEARCH_VIEW.name)
}