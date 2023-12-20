package com.example.Neptune_Prototype.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.ui.Views
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.commons.TopBar
import com.example.Neptune_Prototype.ui.theme.BackgroundColor
import com.example.Neptune_Prototype.ui.theme.PrimaryFontColor
import com.example.Neptune_Prototype.ui.theme.SpotifyButtonColor


@Composable
fun StartView(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        Column {
            Box(modifier = Modifier.weight(1f)) {
                TopBar(onClickBack = { onClickBack() }, description = "Start")
            }
            Box(modifier = Modifier.weight(6f)) {
                StartViewBody(navController)
            }
        }
    }
}


@Composable
fun StartViewBody(navController: NavController) {

    val startViewModel = viewModel<StartViewModel>(
        factory = viewModelFactory {
            StartViewModel(NeptuneApp.spotifyLinkingInfoModule.spotifyLinkingInfoRepo)
        }
    )

    Row {
        Spacer(modifier = Modifier.weight(1f))
        Column(modifier = Modifier.weight(4f)){
            Spacer(modifier = Modifier.weight(1f))
            Column(modifier = Modifier.weight(2f)){
                StandardButton(
                    onClick = { onClickJoinSession(navController) },
                    text = "Session beitreten"
                )
                StandardButton(
                    onClick = { onClickCreateSession(navController) },
                    text = "Session erstellen",
                    enabled = startViewModel.isLinkedToSpotify
                )
            }
            Spacer(modifier = Modifier.weight(3f))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = { onClickLinkToSpotify(startViewModel) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SpotifyButtonColor,
                        contentColor = PrimaryFontColor,
                    )
                ) {
                    Text(startViewModel.spotifyButtonText, fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


fun onClickJoinSession(navController: NavController) {
    navController.navigate(Views.JOIN_VIEW)
}


fun onClickCreateSession(navController: NavController) {
    //TODO
}


fun onClickLinkToSpotify(startViewModel: StartViewModel) {
    startViewModel.toggleLinkedToSpotify()
}

fun onClickBack() {
    //TODO
}


@Preview
@Composable
fun startViewPreview() {
    StartView(rememberNavController())
}


