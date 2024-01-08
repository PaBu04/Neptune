package com.example.Neptune_Prototype.ui.views.joinView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.app.AppState
import com.example.Neptune_Prototype.data.model.app.SpotifyLevel
import com.example.Neptune_Prototype.data.model.backend.ParticipantBackendConnector
import com.example.Neptune_Prototype.data.model.session.Session
import com.example.Neptune_Prototype.data.model.user.FullParticipant
import com.example.Neptune_Prototype.data.model.user.RestrictedParticipant
import com.example.Neptune_Prototype.data.model.user.User
import com.example.Neptune_Prototype.ui.ViewsCollection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JoinViewModel(
    private val appState: AppState,
) : ViewModel() {

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

    fun onConfirmSessionCode(navController: NavController) {
        navController.navigate(ViewsCollection.VOTE_VIEW.name)
        NeptuneApp.modelContainer.session = Session()
        NeptuneApp.modelContainer.backendConnector =
            ParticipantBackendConnector(
                NeptuneApp.modelContainer.backendVolleyQueue,
                NeptuneApp.modelContainer.appState.deviceId
            )
        if (appState.spotifyLevel.value == SpotifyLevel.PREMIUM || appState.spotifyLevel.value == SpotifyLevel.FREE) {
            NeptuneApp.modelContainer.user = FullParticipant(
                NeptuneApp.modelContainer.session!!,
                NeptuneApp.modelContainer.backendConnector as ParticipantBackendConnector,
                NeptuneApp.modelContainer.spotifyConnector
            )
        } else {
            NeptuneApp.modelContainer.user = RestrictedParticipant(
                NeptuneApp.modelContainer.session!!,
                NeptuneApp.modelContainer.backendConnector as ParticipantBackendConnector
            )
        }

        NeptuneApp.modelContainer.user!!.joinSession(sessionCodeInput.toInt())
    }

}