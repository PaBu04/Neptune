package com.example.Neptune_Prototype.data.model.session

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.Neptune_Prototype.NeptuneApp
import com.example.Neptune_Prototype.data.model.track.TrackUiInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HostSession : Session() {

    private val queueList by mutableStateOf(mutableStateListOf<TrackUiInstance>())

    var isPaused by mutableStateOf(false)
        private set

    private val spotifyConnector = NeptuneApp.modelContainer.spotifyConnector

    fun addToQueueList(trackUiInstance: TrackUiInstance) {
        queueList.add(trackUiInstance)
        if (queueList.size == 1) {
            GlobalScope.launch {
                spotifyConnector.playTrack(trackUiInstance.track.value.spotifyId)
            }
        }
    }

    fun removeFromQueueList(trackUiInstance: TrackUiInstance) {
        queueList.remove(trackUiInstance)
        if (queueList.size == 0) {
            pausePlayback()
        }
    }

    fun pausePlayback() {
        isPaused = true
        GlobalScope.launch {
            spotifyConnector.pause()
        }
    }

    fun resumePlayback() {
        if (queueList.size > 0) {
            isPaused = false
            GlobalScope.launch {
                spotifyConnector.resume()
            }
        }
    }

    fun skipTrack() {
        if (queueList.size > 0) {
            queueList.removeAt(0)
        }
        if (queueList.size > 0) {
            GlobalScope.launch {
                spotifyConnector.playTrack(queueList[0].track.value.spotifyId)
            }
        }
    }

    @JvmName("getterForQueueList")
    fun getQueueList(): SnapshotStateList<TrackUiInstance> {
        return queueList
    }

}