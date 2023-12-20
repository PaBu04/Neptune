package com.example.Neptune_Prototype.ui.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.ui.Views
import com.example.Neptune_Prototype.ui.commons.TopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoteView(navController: NavController) {

    val voteViewModel = viewModel<VoteViewModel>(
        factory = viewModelFactory {
            VoteViewModel(NeptuneApp.serverConnectionModule.serverConnectionRepo)
        }
    )

    BackHandler(onBack = { onBack(navController) })

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TopBar(
                    onClickBack = { onClickBackVoteView(navController) },
                    description = "[Modus]"
                )
            }
            Box(modifier = Modifier.weight(4f)) {
                Text(text = voteViewModel.displayText)
            }
        }
    }
}

fun onClickBackVoteView(navController: NavController) {
    navController.popBackStack(Views.START_VIEW, inclusive = false, saveState = false)
}

fun onBack(navController: NavController) {
    navController.popBackStack(Views.START_VIEW, inclusive = false, saveState = false)
}


@Preview
@Composable
fun sessionVoteViewPreview() {
    VoteView(rememberNavController())
}