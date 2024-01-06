package com.example.Neptune_Prototype


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.Neptune_Prototype.ui.NeptuneNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeptuneUi()
        }
    }
}

@Composable
fun NeptuneUi(){
    NeptuneNavGraph()
}
