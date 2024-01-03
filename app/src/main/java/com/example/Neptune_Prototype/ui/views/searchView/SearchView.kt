package com.example.Neptune_Prototype.ui.views.searchView

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.repositories.SpotifyConnector
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchViewBody(navController: NavController) {
    val searchViewModel = viewModel<SearchViewModel>(
        factory = viewModelFactory {
            SearchViewModel(NeptuneApp.spotifyConnectionDataModule.spotifyConnector)
        }
    )

    Column {
        TextField(value = searchViewModel.trackSearchInput, onValueChange = { searchViewModel.onSearchInputChange(it) })
        StandardButton(onClick = { searchViewModel.onSearch() }, text = "Suchen")
        Text(text = searchViewModel.outputText, color = Color.White)
    }
}


fun searchViewOnBack(navController: NavController) {
    navController.popBackStack()
}