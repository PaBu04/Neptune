package com.example.Neptune_Prototype.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.ui.theme.BackgroundColor


@Composable
fun TrackListComposable(
    tracks: List<MutableState<Track>>,
    trackListType: TrackListType,
    onToggleUpvote: (Track) -> Unit,
    onToggleDropdown: (index: Int) -> Unit = {},
    isDropdownExpanded: (index: Int) -> Boolean = { false },
    onAddToQueue: (Track) -> Unit = {},
    onRemoveFromQueue: (index: Int) -> Unit = {},
    onToggleBlock: (Track) -> Unit = {},
    onMoveUp: (index: Int) -> Unit = {},
    onMoveDown: (index: Int) -> Unit = {}
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

            for (trackIndex in tracks.indices) {
                TrackComposable(
                    tracks[trackIndex].value,
                    trackIndex,
                    trackListType,
                    onToggleUpvote,
                    onToggleDropdown,
                    isDropdownExpanded,
                    onAddToQueue,
                    onRemoveFromQueue,
                    onToggleBlock,
                    onMoveUp,
                    onMoveDown
                )
            }
        }
    }
}