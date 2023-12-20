package com.example.Neptune_Prototype.data.repositories

import com.example.Neptune_Prototype.data.room.SpotifyLinkingInfo
import com.example.Neptune_Prototype.data.room.SpotifyLinkingInfoDao

class SpotifyLinkingInfoRepo(
    private val spotifyLinkingInfoDao: SpotifyLinkingInfoDao
){

    suspend fun hasLinkedEntry(): Boolean{
        return spotifyLinkingInfoDao.entryCount() == 1
    }

    suspend fun isLinked(): Boolean{
        return spotifyLinkingInfoDao.isLinked()
    }

    suspend fun updateLinked(isLinked: Boolean){
        spotifyLinkingInfoDao.upsert(SpotifyLinkingInfo(0, isLinked))
    }
}