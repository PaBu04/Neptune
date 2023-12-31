package com.example.Neptune_Prototype.ui.views.joinView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class JoinViewModel() : ViewModel() {

    var codeIsValid by mutableStateOf(false)
        private set
    var sessionCodeInput by mutableStateOf("")
        private set

    fun onSessionCodeInputChange(input: String) {
        if (input.isEmpty() || input.length <= 6) {
            sessionCodeInput = input
        }
        codeIsValid = (sessionCodeInput.length == 6)
    }

}