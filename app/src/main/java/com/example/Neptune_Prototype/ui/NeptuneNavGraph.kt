package com.example.Neptune_Prototype.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Neptune_Prototype.ui.views.JoinView
import com.example.Neptune_Prototype.ui.views.StartView
import com.example.Neptune_Prototype.ui.views.VoteView

@Composable
fun NeptuneNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Views.START_VIEW){

        composable(Views.START_VIEW){
            StartView(navController)
        }

        composable(Views.JOIN_VIEW){
            JoinView(navController)
        }

        composable(Views.VOTE_VIEW){
            VoteView(navController)
        }

    }
}