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
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
    }

    fun handleIntent(intent: Intent){
        Log.w("RETURN CODE OAUTH", "is called")
        val code = intent.data?.getQueryParameter("code")
        if(code != null){
            GlobalScope.launch {
                NeptuneApp.spotifyConnectionDataModule.spotifyConnector.getAccessToken(code)
            }
        }
    }
}