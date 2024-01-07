package com.example.Neptune_Prototype.data.room.spotify

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.Neptune_Prototype.data.room.spotify.SpotifyConnectionData


@Dao
interface SpotifyConnectionDataDao{
    @Upsert
    suspend fun upsert(spotifyConnectionData: SpotifyConnectionData)

    @Delete
    suspend fun delete(spotifyConnectionData: SpotifyConnectionData)

    @Query("SELECT is_linked FROM SPOTIFY_CONNECTION_DATA WHERE artificial_id=0")
    suspend fun isLinked(): Boolean

    @Query("SELECT COUNT(artificial_id) FROM SPOTIFY_CONNECTION_DATA")
    suspend fun entryCount(): Int

    @Query("UPDATE SPOTIFY_CONNECTION_DATA SET refresh_token = :newRefreshToken WHERE artificial_id=0")
    suspend fun setRefreshToken(newRefreshToken: String)

    @Query("SELECT refresh_token FROM SPOTIFY_CONNECTION_DATA WHERE artificial_id=0")
    suspend fun getRefreshToken(): String
}