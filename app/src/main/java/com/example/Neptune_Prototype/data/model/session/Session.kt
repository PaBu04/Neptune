package com.example.Neptune_Prototype.data.model.session


open class Session() {

    private var sessionId = -1
    private var timestamp = -1

    fun setSessionIdentifiers(sessionId: Int, timestamp: Int){
        this.sessionId = sessionId
        this.timestamp = timestamp
    }

}