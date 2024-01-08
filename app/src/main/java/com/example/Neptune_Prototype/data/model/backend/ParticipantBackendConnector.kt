package com.example.Neptune_Prototype.data.model.backend

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import io.ktor.client.HttpClient
import org.json.JSONObject

class ParticipantBackendConnector(
    private val volleyQueue: RequestQueue,
    private val deviceId: String
) : BackendConnector(volleyQueue, deviceId) {



    fun participantJoinSession(sessionId: Int) {
        val url = baseUrl + "participantJoinSession"
        val postData = JSONObject()
        postData.put("participantDeviceID", deviceId)
        postData.put("sessionID", sessionId)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, postData,
            {response ->
                Log.i("JSON JOIN", response.toString())
            },
            {error ->
                Log.e("VOLLEY", "Server Request Error: ${error.localizedMessage}")
            })

        volleyQueue.add(jsonObjectRequest)
    }


    fun participantLeaveSession() {
        val url = baseUrl + "participantLeaveSession"
        val postData = JSONObject()
        postData.put("participantDeviceID", deviceId)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, postData,
            {response ->
                Log.i("JSON LEAVE", response.toString())
            },
            {error ->
                Log.e("VOLLEY", "Server Request Error: ${error.localizedMessage}")
            })

        volleyQueue.add(jsonObjectRequest)
    }
}