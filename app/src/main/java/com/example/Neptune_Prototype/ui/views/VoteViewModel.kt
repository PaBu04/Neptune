package com.example.Neptune_Prototype.ui.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Neptune_Prototype.data.repositories.ServerConnectionRepo
import kotlinx.coroutines.launch

class VoteViewModel(
    private val serverConnectionRepo: ServerConnectionRepo
) : ViewModel() {

    var displayText by mutableStateOf("")
        private set

    init{
        serverRequest()
    }

    fun serverRequest(){
        viewModelScope.launch {
            displayText = serverConnectionRepo.getServerText()
        }
    }


}