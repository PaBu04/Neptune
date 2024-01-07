package com.example.Neptune_Prototype.ui.views.voteView

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.commons.TrackListComp
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun VoteViewBody(navController: NavController) {
    val voteViewModel = viewModel<VoteViewModel>(
        factory = viewModelFactory {
            VoteViewModel(
                NeptuneApp.modelContainer.session as Session,
                NeptuneApp.modelContainer.user!!.voteList
            )
        }
    )

    Column {
        Box(modifier = Modifier.weight(7f)) {
            TrackListComp(
                tracks = voteViewModel.getVoteList(),
                onToggleUpvote = { voteViewModel.onToggleUpvote(it) },
                onAddToQueue = {},
                onRemoveFromQueue = {} )
        }
        StandardButton(onClick = { onSearchTrack(navController) }, text = "Track suchen")
    }
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
}