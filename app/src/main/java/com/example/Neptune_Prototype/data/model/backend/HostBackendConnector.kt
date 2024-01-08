package com.example.Neptune_Prototype.data.model.backend

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.Neptune_Prototype.data.jsonData.server.CreateNewSessionResponse
import com.example.Neptune_Prototype.data.jsonData.server.UserSessionStateResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject

class HostBackendConnector(
    private val volleyQueue: RequestQueue,
    private val deviceId: String
) : BackendConnector(volleyQueue, deviceId) {


    fun createNewSession(callback: (Int, Int) -> Unit) {
        val url = baseUrl + "createNewSession"
        val postData = JSONObject()
        postData.put("hostDeviceID", deviceId)
        postData.put("modus", "General")
        postData.put("cooldownTimer", 0)
        postData.put("artists", JSONArray())
        postData.put("genres", JSONArray())
        postData.put("playlist", "")

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, postData,
            { response ->
                Log.i("JSON CREATE", response.toString())
                val sessionId = response.getInt("sessionID")
                val timestamp = response.getInt("timestamp")
                callback(sessionId, timestamp)
            },
            { error ->
                Log.e("VOLLEY", "Server Request Error: ${error.message}")
            })

        volleyQueue.add(jsonObjectRequest)
    }


    fun deleteSession() {
        val url = baseUrl + "deleteSession"
        val postData = JSONObject()
        postData.put("hostDeviceID", deviceId)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, postData,
            { response ->
                Log.i("JSON DEL", response.toString()) },
            { error ->
                Log.e("VOLLEY", "Server Request Error: ${error.localizedMessage}")
            })

        volleyQueue.add(jsonObjectRequest)
    }


    suspend fun playedTrack() {

    }


    suspend fun setBlockTrack() {

    }

}