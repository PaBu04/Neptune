package com.example.Neptune_Prototype.ui.views.modeSelectView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class SelectedMode{
    GENERAL_MODE, ARTIST_MODE, GENRE_MODE, PLAYLIST_MODE
}


class ModeSelectViewModel : ViewModel() {

    var selectedMode by mutableStateOf(SelectedMode.GENERAL_MODE)

}