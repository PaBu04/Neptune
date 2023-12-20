package com.example.Neptune_Prototype.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface SpotifyLinkingInfoDao{
    @Upsert
    suspend fun upsert(spotifyLinkingInfo: SpotifyLinkingInfo)

    @Delete
    suspend fun delete(spotifyLinkingInfo: SpotifyLinkingInfo)

    @Query("SELECT isLinked FROM SPOTIFY_LINKING_INFO WHERE artificial_id=0")
    suspend fun isLinked(): Boolean

    @Query("SELECT COUNT(artificial_id) FROM SPOTIFY_LINKING_INFO")
    suspend fun entryCount(): Int
}