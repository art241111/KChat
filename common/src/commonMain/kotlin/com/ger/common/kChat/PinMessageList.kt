package com.ger.common.kChat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.messages.MessageFactory

@Composable
fun PinMessageList(
    modifier: Modifier = Modifier,
    pinMessages: List<Message>,
    onSendMessage: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(pinMessages,key = {it}) {
            PinItem(message = it,onSendMessage = onSendMessage)
        }

    }
}

@Composable
private fun PinItem(
    modifier: Modifier = Modifier,
    message: Message,
    onSendMessage: (String) -> Unit
) {
    Row (modifier, verticalAlignment = Alignment.CenterVertically) {
        MessageFactory(modifier = Modifier.weight(1f), message = message,onSendMessage = onSendMessage)
        IconButton(onClick = {message.isPin.value = false}){
            Icon(Icons.Default.Close,"x")
        }
    }
}