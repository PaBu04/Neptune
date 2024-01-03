//just a comment to check on github, can be deleted
package com.example.Neptune_Prototype

import android.app.Application
import com.example.Neptune_Prototype.di.ServerConnectionModule
import com.example.Neptune_Prototype.di.SpotifyConnectionDataModule

class NeptuneApp: Application() {

    companion object {
        lateinit var spotifyConnectionDataModule: SpotifyConnectionDataModule
        lateinit var serverConnectionModule: ServerConnectionModule
    }

    override fun onCreate() {
        super.onCreate()
        spotifyConnectionDataModule = SpotifyConnectionDataModule(this)
        serverConnectionModule = ServerConnectionModule(this)
    }
}