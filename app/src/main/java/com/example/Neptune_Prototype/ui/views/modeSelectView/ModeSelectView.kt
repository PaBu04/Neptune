package com.example.Neptune_Prototype.ui.views.modeSelectView

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.ModeSelectButton
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.theme.DefaultButtonColor
import com.example.Neptune_Prototype.ui.theme.DefaultDisabledButtonColor
import com.example.Neptune_Prototype.ui.theme.DefaultDisabledButtonFontColor
import com.example.Neptune_Prototype.ui.theme.PrimaryFontColor
import com.example.Neptune_Prototype.ui.views.util.viewModelFactory


@Composable
fun ModeSelectViewBody(navController: NavController) {
    val modeSelectViewModel = viewModel<ModeSelectViewModel>(
        factory = viewModelFactory {
            ModeSelectViewModel()
        }
    )

    Column() {
        ModeSelectButton(
            onClick = { modeSelectViewModel.selectedMode = SelectedMode.GENERAL_MODE },
            text = "General Mode",
            isSelected = modeSelectViewModel.selectedMode == SelectedMode.GENERAL_MODE
        )
        ModeSelectButton(
            onClick = { modeSelectViewModel.selectedMode = SelectedMode.ARTIST_MODE },
            text = "Artist Mode",
            isSelected = modeSelectViewModel.selectedMode == SelectedMode.ARTIST_MODE
        )
        ModeSelectButton(
            onClick = { modeSelectViewModel.selectedMode = SelectedMode.GENRE_MODE },
            text = "Genre Mode",
            isSelected = modeSelectViewModel.selectedMode == SelectedMode.GENRE_MODE
        )
        ModeSelectButton(
            onClick = { modeSelectViewModel.selectedMode = SelectedMode.PLAYLIST_MODE },
            text = "Playlist Mode",
            isSelected = modeSelectViewModel.selectedMode == SelectedMode.PLAYLIST_MODE
        )
        StandardButton(onClick = { onConfirmMode(navController) }, text = "Bestätigen")
    }
}

fun modeSelectViewOnBack(navController: NavController) {
    navController.popBackStack()
}

fun onConfirmMode(navController: NavController){
    navController.navigate(ViewsCollection.MODE_SETTINGS_VIEW.name)
}