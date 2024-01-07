package com.example.Neptune_Prototype.data.room.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionData
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionDataDao

@Database(
    entities = [AppData::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataDatabase : RoomDatabase() {

    abstract val appDataDao: AppDataDao

}