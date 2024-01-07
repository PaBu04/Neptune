package com.example.Neptune_Prototype

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
        finish()
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
        finish()
    }

    fun handleIntent(intent: Intent){
        Log.i("RETURN CODE OAUTH", "is called from spotify")
        val code = intent.data?.getQueryParameter("code")
        if(code != null){
            GlobalScope.launch {
                NeptuneApp.modelContainer.spotifyConnector.exchangeCodeToAccessToken(code)
                NeptuneApp.modelContainer.appState.setSpotifyLevel()
            }
        }
    }
}