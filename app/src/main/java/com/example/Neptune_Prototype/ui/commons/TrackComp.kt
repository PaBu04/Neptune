package com.example.Neptune_Prototype.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Neptune_Prototype.ui.theme.ElementsColor
import com.example.Neptune_Prototype.ui.theme.PrimaryFontColor
import com.example.Neptune_Prototype.ui.theme.SecondaryFontColor
import com.example.Neptune_Prototype.ui.theme.UpvoteColor

@Composable
fun TrackComp(
    image: String, trackName: String, artists: String, upvotes: String, isUpvoted: Boolean
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
            Text(image)
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(5.dp)
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(trackName, color = PrimaryFontColor, fontSize = 18.sp)
                Text(artists, color = SecondaryFontColor, fontSize = 10.sp)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1.5f)
                .padding(5.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                Text(upvotes, color = UpvoteColor)
                StandardButton(onClick = { /*TODO*/ }, text = "is")
            }
        }
    }
}


@Preview
@Composable
fun TrackPreview() {
    TrackComp(
        image = "N/A",
        trackName = "Noice Track",
        artists = "Nils, Bär",
        upvotes = "5",
        isUpvoted = true
    )
}