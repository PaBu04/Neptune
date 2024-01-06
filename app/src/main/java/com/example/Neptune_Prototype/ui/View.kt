package com.example.Neptune_Prototype.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.Neptune_Prototype.ui.commons.TopBarAndBody
import com.example.Neptune_Prototype.ui.views.startView.StartViewBody

class View(
    val name: String, // is there because the navHost only works with strings
    private val composableViewBody: @Composable (navController: NavController) -> Unit,
    private val onBack: (navController: NavController) -> Unit,
    private val topBarDescription: String
) {

    @Composable
    fun show(navController: NavController) {
        TopBarAndBody(
            composableBody = @Composable { composableViewBody(navController) },
            onBack = { onBack(navController) },
            topBarDescription = topBarDescription
        )
    }

}