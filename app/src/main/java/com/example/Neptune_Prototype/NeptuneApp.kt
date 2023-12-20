package com.example.Neptune_Prototype

import android.app.Application
import com.example.Neptune_Prototype.di.ServerConnectionModule
import com.example.Neptune_Prototype.di.SpotifyLinkingInfoModule

class NeptuneApp: Application() {

    companion object {
        lateinit var spotifyLinkingInfoModule: SpotifyLinkingInfoModule
        lateinit var serverConnectionModule: ServerConnectionModule
    }

    override fun onCreate() {
        super.onCreate()
        spotifyLinkingInfoModule = SpotifyLinkingInfoModule(this)
        serverConnectionModule = ServerConnectionModule(this)
    }
}