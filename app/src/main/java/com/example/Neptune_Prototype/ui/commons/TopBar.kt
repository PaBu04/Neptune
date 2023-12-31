package com.example.Neptune_Prototype.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Neptune_Prototype.ui.theme.BackgroundColor

@Composable
fun TopBar(onClickBack: () -> Unit, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff090e29))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "<-",
                modifier = Modifier.clickable(onClick = onClickBack),
                color = Color.White
            )
            Text(text = "NEPTUNE", color = Color.White, fontSize = 22.sp)
            Text(text = "Logo...", color = Color.White)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = description, color = Color.White, fontSize = 18.sp)
        }
    }
}


@Composable
fun TopBarAndBody(composableBody: @Composable () -> Unit, onBack: () -> Unit, topBarDescription: String){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        Column {
            Box(modifier = Modifier.weight(1f)) {
                TopBar(onClickBack = { onBack() }, description = topBarDescription)
            }
            Box(modifier = Modifier.weight(6f)) {
                composableBody()
            }
        }
    }
}


@Preview
@Composable
fun TopBarPreview() {
    TopBar({ }, "description")
}