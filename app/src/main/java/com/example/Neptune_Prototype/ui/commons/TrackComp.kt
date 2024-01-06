package com.example.Neptune_Prototype.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.Neptune_Prototype.data.model.track.Track
import com.example.Neptune_Prototype.data.model.track.TrackListType
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import com.example.Neptune_Prototype.ui.theme.ElementsColor
import com.example.Neptune_Prototype.ui.theme.PrimaryFontColor
import com.example.Neptune_Prototype.ui.theme.SecondaryFontColor
import com.example.Neptune_Prototype.ui.theme.UpvoteColor

@Composable
fun TrackComp(
    trackUiInstance: TrackUiInstance,
    onToggleUpvote: (TrackUiInstance) -> Unit,
    onAddToQueue: (Track) -> Unit,
    onRemoveFromQueue: (TrackUiInstance) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = ElementsColor)
            .clip(shape = RoundedCornerShape(3.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .padding(5.dp)
        ) {
            AsyncImage(model = trackUiInstance.track.value.imageUrl, contentDescription = "track image")
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(5.dp)
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    trackUiInstance.track.value.trackName,
                    color = PrimaryFontColor,
                    fontSize = 18.sp,
                    maxLines = 1
                )
                Text(
                    trackUiInstance.track.value.getArtistNames(),
                    color = SecondaryFontColor,
                    fontSize = 10.sp,
                    maxLines = 1
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1.5f)
                .padding(5.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                Text(trackUiInstance.track.value.upvoteCount.toString(), color = UpvoteColor)
                IconButton(onClick = { onToggleUpvote(trackUiInstance) }) {
                    var icon: ImageVector
                    if (trackUiInstance.track.value.isUpvoted) {
                        icon = Icons.Default.CheckCircle
                    } else {
                        icon = Icons.Default.AddCircle
                    }
                    Icon(icon, "", tint = Color.White)
                }

                if (trackUiInstance.trackListType == TrackListType.HOST_QUEUE
                    || trackUiInstance.trackListType == TrackListType.HOST_VOTE
                    || trackUiInstance.trackListType == TrackListType.HOST_SEARCH
                ) {

                    IconButton(onClick = { trackUiInstance.isDropDownExpanded = true }) {
                        Icon(Icons.Default.MoreVert, "", tint = Color.White)
                    }
                    DropdownMenu(
                        expanded = trackUiInstance.isDropDownExpanded,
                        onDismissRequest = { trackUiInstance.isDropDownExpanded = false }) {
                        val dropdownOptions = trackUiInstance.getDropdownOptions(onAddToQueue, onRemoveFromQueue)
                        for(optionIndex in 0 until dropdownOptions.first.size) {
                            DropdownMenuItem(
                                text = { Text(dropdownOptions.first[optionIndex]) },
                                onClick = { dropdownOptions.second[optionIndex]() })
                        }
                    }
                }
            }
        }
    }
}