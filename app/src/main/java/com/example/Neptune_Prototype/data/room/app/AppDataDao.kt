package com.example.Neptune_Prototype.data.room.app

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AppDataDao{
    @Upsert
    suspend fun upsert(appData: AppData)

    @Delete
    suspend fun delete(appData: AppData)

    @Query("SELECT device_id FROM APP_DATA WHERE artificial_id=0")
    suspend fun getDeviceId(): String

    @Query("SELECT COUNT(artificial_id) FROM APP_DATA")
    suspend fun entryCount(): Int
}