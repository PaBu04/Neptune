package com.example.Neptune_Prototype.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import com.example.Neptune_Prototype.ui.theme.BackgroundColor


@Composable
fun TrackListComp(
    tracks: List<TrackUiInstance>,
    onToggleUpvote: (TrackUiInstance) -> Unit,
    onAddToQueue: (Track) -> Unit,
    onRemoveFromQueue: (TrackUiInstance) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = BackgroundColor)
            .padding(3.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f, fill = false)
        ) {

            tracks.forEach {
                TrackComp(it, onToggleUpvote, onAddToQueue, onRemoveFromQueue)
            }
        }
    }
}