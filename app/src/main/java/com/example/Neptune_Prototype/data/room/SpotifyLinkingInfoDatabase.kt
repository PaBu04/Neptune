package com.example.Neptune_Prototype.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [SpotifyLinkingInfo::class],
    version = 1,
    exportSchema = false
)
abstract class SpotifyLinkingInfoDatabase : RoomDatabase() {

    abstract val spotifyLinkingInfoDao: SpotifyLinkingInfoDao

}