package com.example.Neptune_Prototype.ui.views.joinView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.ui.ViewsCollection
import com.example.Neptune_Prototype.ui.commons.StandardButton
import com.example.Neptune_Prototype.ui.views.startView.onClickCreateSession



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

fun joinViewOnBack(navController: NavController) {
    navController.popBackStack()
}

fun onConfirmSessionCode(navController: NavController) {
    navController.navigate(ViewsCollection.VOTE_VIEW.name)
    NeptuneApp.modelContainer.session = Session()
}
