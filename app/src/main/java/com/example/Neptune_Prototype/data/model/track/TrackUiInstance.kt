package com.example.Neptune_Prototype.data.model.track

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TrackUiInstance(var track: MutableState<Track>, val trackListType: TrackListType) {

    var isDropDownExpanded by mutableStateOf(false)

    fun getDropdownOptions(addToQueue: (Track) -> Unit, removeFromQueue: (TrackUiInstance) -> Unit): Pair<List<String>, List<() -> Unit>> {
        if (trackListType == TrackListType.HOST_SEARCH) {
            return hostSearchDropdownOptions(addToQueue)
        }
        if (trackListType == TrackListType.HOST_VOTE) {
            return hostVotelistDropdownOptions(addToQueue)
        }
        if (trackListType == TrackListType.HOST_QUEUE) {
            return hostQueueDropdownOptions(removeFromQueue)
        }
        return Pair(mutableListOf<String>(), mutableListOf<() -> Unit>())
    }

    private fun hostSearchDropdownOptions(addToQueue: (Track) -> Unit): Pair<List<String>, List<() -> Unit>> {
        val dropdownOptions = Pair(mutableListOf<String>(), mutableListOf<() -> Unit>())
        dropdownOptions.first.add("In die Queue")
        dropdownOptions.second.add { addToQueue(track.value) }
        if (track.value.isLocked) {
            dropdownOptions.first.add("Track entsperren")
            dropdownOptions.second.add { track.value.isLocked = false }
        } else {
            dropdownOptions.first.add("Track sperren")
            dropdownOptions.second.add { track.value.isLocked = true }
        }
        if (track.value.hasCooldown) {
            dropdownOptions.first.add("Cooldown aufheben")
            dropdownOptions.second.add { track.value.hasCooldown = false }
        }
        return dropdownOptions
    }

    private fun hostVotelistDropdownOptions(addToQueue: (Track) -> Unit): Pair<List<String>, List<() -> Unit>> {
        val dropdownOptions = Pair(mutableListOf<String>(), mutableListOf<() -> Unit>())
        dropdownOptions.first.add("In die Queue")
        dropdownOptions.second.add { addToQueue(track.value) }
        if (track.value.isLocked) {
            dropdownOptions.first.add("Track entsperren")
            dropdownOptions.second.add { track.value.isLocked = false }
        } else {
            dropdownOptions.first.add("Track sperren")
            dropdownOptions.second.add { track.value.isLocked = true }
        }
        return dropdownOptions
    }

    private fun hostQueueDropdownOptions(removeFromQueue: (TrackUiInstance) -> Unit): Pair<List<String>, List<() -> Unit>> {
        val dropdownOptions = Pair(mutableListOf<String>(), mutableListOf<() -> Unit>())
        dropdownOptions.first.add("Entfernen")
        dropdownOptions.second.add { removeFromQueue(this) }
        if (track.value.isLocked) {
            dropdownOptions.first.add("Track entsperren")
            dropdownOptions.second.add { track.value.isLocked = false }
        } else {
            dropdownOptions.first.add("Track sperren")
            dropdownOptions.second.add { track.value.isLocked = true }
        }
        return dropdownOptions
    }

}