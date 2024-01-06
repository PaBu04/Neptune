package com.example.Neptune_Prototype.ui.views.searchView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.ui.commons.TrackListComp
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchViewBody(navController: NavController) {
    val searchViewModel = viewModel<SearchViewModel>(
        factory = viewModelFactory {
            SearchViewModel(
                NeptuneApp.modelContainer.spotifyConnector,
                NeptuneApp.modelContainer.session as Session
            )
        }
    )

    Column(Modifier.padding(10.dp)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchViewModel.trackSearchInput,
            onValueChange = { searchViewModel.onSearchInputChange(it) })
        Box {
            TrackListComp(
                tracks = searchViewModel.trackList,
                onToggleUpvote = { searchViewModel.onToggleUpvote(it) },
                onAddToQueue = {},
                onRemoveFromQueue = {} )
        }
    }
}


fun searchViewOnBack(navController: NavController) {
    navController.popBackStack()
}