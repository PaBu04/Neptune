package com.example.Neptune_Prototype.ui.views.searchView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Neptune_Prototype.data.repositories.SpotifyConnector
import kotlinx.coroutines.launch

class SearchViewModel(
    private val spotifyConnector: SpotifyConnector
) : ViewModel() {

    var outputText by mutableStateOf("kein Ergebnis")
        private set

    var trackSearchInput by mutableStateOf("i")
        private set

    fun onSearchInputChange(input: String) {
        trackSearchInput = input
    }

    fun onSearch(){
        viewModelScope.launch {
            outputText = spotifyConnector.getBestTrack(trackSearchInput)
        }
    }
}