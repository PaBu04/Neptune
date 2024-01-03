package com.example.Neptune_Prototype.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [SpotifyConnectionData::class],
    version = 1,
    exportSchema = false
)
abstract class SpotifyConnectionDataDatabase : RoomDatabase() {

    abstract val spotifyConnectionDataDao: SpotifyConnectionDataDao

}