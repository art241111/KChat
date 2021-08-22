package com.ger.common.kChat.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State

abstract class Message {
    abstract val text: String
    abstract val sender: Sender
    abstract val isPin: MutableState<Boolean>

    override fun toString(): String {
        return "${sender.ordinal}:$text:${isPin.value}"
    }
}
