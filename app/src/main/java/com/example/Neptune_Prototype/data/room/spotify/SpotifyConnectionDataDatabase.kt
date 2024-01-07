package com.example.Neptune_Prototype.data.room.spotify

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionData
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionDataDao


@Database(
    entities = [SpotifyConnectionData::class],
    version = 2,
    exportSchema = false
)
abstract class SpotifyConnectionDataDatabase : RoomDatabase() {

    abstract val spotifyConnectionDataDao: SpotifyConnectionDataDao

}