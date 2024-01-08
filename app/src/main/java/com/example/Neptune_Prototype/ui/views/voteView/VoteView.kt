package com.example.Neptune_Prototype.ui.views.voteView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.commons.TrackListComposable
import com.example.Neptune_Prototype.ui.commons.TrackListType
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory
import kotlinx.coroutines.delay


@Composable
fun VoteViewBody(navController: NavController) {
    val voteViewModel = viewModel<VoteViewModel>(
        factory = viewModelFactory {
            VoteViewModel(
                NeptuneApp.modelContainer.user!!
            )
        }
    )

    Column {
        Box(modifier = Modifier.weight(7f)) {
            TrackListComposable(
                tracks = voteViewModel.getVoteList(),
                trackListType = TrackListType.PARTICIPANT_VOTE,
                onToggleUpvote = { voteViewModel.onToggleUpvote(it) })
        }
        StandardButton(onClick = { onSearchTrack(navController) }, text = "Track suchen")
    }

    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            voteViewModel.updateVoteList()
            delay(5000)
        }
    })
}

fun onSearchTrack(navController: NavController) {
    navController.navigate(ViewsCollection.SEARCH_VIEW.name)
}

fun voteViewOnBack(navController: NavController) {
    navController.popBackStack(
        ViewsCollection.START_VIEW.name,
        inclusive = false,
        saveState = false
    )
    NeptuneApp.modelContainer.user!!.leaveSession()
}