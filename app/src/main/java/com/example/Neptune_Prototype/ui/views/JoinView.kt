package com.example.Neptune_Prototype.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.Neptune_Prototype.ui.Views
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.commons.TopBar
import com.example.Neptune_Prototype.ui.theme.BackgroundColor


@Composable
fun JoinView(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        Column {
            Box(modifier = Modifier.weight(1f)) {
                TopBar(onClickBack = { onClickBack() }, description = "Session beitreten")
            }
            Box(modifier = Modifier.weight(6f)) {
                JoinViewBody(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinViewBody(navController: NavController) {

    val joinViewModel = viewModel<JoinViewModel>()

    Row {
        Spacer(modifier = Modifier.weight(1f))
        Column(modifier = Modifier.weight(4f)) {
            Spacer(modifier = Modifier.weight(1f))
            Column(modifier = Modifier.weight(5f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(modifier = Modifier.weight(2f)){
                        TextField(
                            value = joinViewModel.sessionCodeInput,
                            onValueChange = { joinViewModel.onSessionCodeInputChange(it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    Box(modifier = Modifier.weight(1f)){
                        StandardButton(
                            onClick = { onConfirmSessionCode(navController) },
                            text = "->",
                            enabled = joinViewModel.codeIsValid
                        )
                    }
                }
                StandardButton(
                    onClick = { onClickCreateSession(navController) },
                    text = "Code scannen"
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

fun onClickBackJoinView(navController: NavController) {
    navController.popBackStack()
}

fun onConfirmSessionCode(navController: NavController) {
    navController.navigate(Views.VOTE_VIEW)
}


@Preview
@Composable
fun joinSessionViewPreview() {
    JoinView(rememberNavController())
}