package com.example.Neptune_Prototype.di

import android.content.Context
import com.example.Neptune_Prototype.data.repositories.ServerConnectionRepo
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

class ServerConnectionModule(
    private val context: Context
) {

    val httpClient by lazy{
        HttpClient(Android){
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }
    }

    val serverConnectionRepo by lazy {
        ServerConnectionRepo(httpClient)
    }

}