//just a comment to check on github, can be deleted
package com.example.Neptune_Prototype

import android.app.Application
import com.example.Neptune_Prototype.di.ModelContainer

class NeptuneApp: Application() {

    companion object {
        lateinit var modelContainer: ModelContainer
    }

    override fun onCreate() {
        super.onCreate()
        modelContainer = ModelContainer(this)
    }
}