package com.ger.common.kChat.data

abstract class Message {
    abstract val text: String
    abstract val sender: Sender

    override fun toString(): String {
        return "${sender.ordinal}:$text"
    }
}
