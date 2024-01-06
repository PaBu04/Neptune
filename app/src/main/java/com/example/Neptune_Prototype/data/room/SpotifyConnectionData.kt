package com.example.Neptune_Prototype.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spotify_linking_info")
data class SpotifyConnectionData(
    @ColumnInfo(name = "artificial_id")
    @PrimaryKey
    val artificialId: Int,
    val isLinked: Boolean,
    val refreshToken: String
)



