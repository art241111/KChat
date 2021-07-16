package com.ger.common.data

import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.ger.common.kChat.data.Message

interface Robot {
    val name: String
    val ip: String
    val color: Color
    val port: Int
    val isConnect: State<Boolean>

    val chat: State<List<Message>>
    fun sendMessage(message: String)
    fun connect()
    fun disconnect()
}
