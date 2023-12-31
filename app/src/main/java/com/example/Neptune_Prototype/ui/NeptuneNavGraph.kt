package com.example.Neptune_Prototype.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NeptuneNavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ViewsCollection.START_VIEW.name){

        composable(ViewsCollection.START_VIEW.name){
            ViewsCollection.START_VIEW.show(navController)
        }

        composable(ViewsCollection.JOIN_VIEW.name){
            ViewsCollection.JOIN_VIEW.show(navController)
        }

        composable(ViewsCollection.VOTE_VIEW.name){
            ViewsCollection.VOTE_VIEW.show(navController)
        }

        composable(ViewsCollection.SEARCH_VIEW.name){
            ViewsCollection.SEARCH_VIEW.show(navController)
        }

        composable(ViewsCollection.MODE_SELECT_VIEW.name){
            ViewsCollection.MODE_SELECT_VIEW.show(navController)
        }

        composable(ViewsCollection.MODE_SETTINGS_VIEW.name){
            ViewsCollection.MODE_SETTINGS_VIEW.show(navController)
        }

        composable(ViewsCollection.CONTROL_VIEW.name){
            ViewsCollection.CONTROL_VIEW.show(navController)
        }

        composable(ViewsCollection.INFO_VIEW.name){
            ViewsCollection.INFO_VIEW.show(navController)
        }

        composable(ViewsCollection.STATS_VIEW.name){
            ViewsCollection.STATS_VIEW.show(navController)
        }

    }

}