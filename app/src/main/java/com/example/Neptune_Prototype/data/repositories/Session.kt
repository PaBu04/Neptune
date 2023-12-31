package com.example.Neptune_Prototype.data.repositories

class Session(val sessionId: String) {
    val voteList = mutableListOf<Track>()
}