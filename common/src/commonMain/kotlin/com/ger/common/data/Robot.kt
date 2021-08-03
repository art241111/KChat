package com.ger.common.data

import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.ger.common.kChat.data.Message

abstract class Robot {
    abstract val name: String
    abstract val ip: String
    abstract val color: Color
    abstract val port: Int
    abstract val isConnect: State<Boolean>

    abstract val chat: State<List<Message>>
    abstract fun sendMessage(message: String)
    abstract fun connect()
    abstract fun disconnect()


    override fun toString(): String {
        return "$name\n" +
                "$ip\n" +
                "$port\n" +
                "${color.red};${color.green};${color.blue}" +
                if (chat.value.isNotEmpty())
                    chat.value.joinToString(prefix = "\n", separator = "\n")
                else
                    ""
    }
}
