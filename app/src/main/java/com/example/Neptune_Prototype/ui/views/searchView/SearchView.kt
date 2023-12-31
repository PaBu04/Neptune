package com.example.Neptune_Prototype.ui.views.searchView

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun SearchViewBody(navController: NavController){
    val searchViewModel = viewModel<SearchViewModel>(
        factory = viewModelFactory {
            SearchViewModel()
        }
    )
}

fun searchViewOnBack(navController: NavController) {
    // TODO navController.popBackStack(ViewsCollection.START_VIEW.name, inclusive = false, saveState = false)
}