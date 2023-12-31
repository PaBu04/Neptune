package com.example.Neptune_Prototype.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Neptune_Prototype.ui.theme.BackgroundColor


@Composable
fun TrackListComp() {
    Column(
        modifier = Modifier
            .background(color = BackgroundColor)
            .padding(3.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState()).weight(1f, fill = false)){
            for(i in 1..20){
                TrackComp(
                    image = "N/A",
                    trackName = "Noice Track",
                    artists = "Nils, Bär",
                    upvotes = "5",
                    isUpvoted = true
                )
            }
        }
        Text("Tracksuche")
    }
}


@Preview
@Composable
fun TrackListPreview() {
    TrackListComp()
}