package com.ger.common.kChat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender
import com.ger.common.utils.HorizontalSeparator

@Composable
fun KChat(
    modifier: Modifier = Modifier,
    onSend: (String) -> Unit,
    messages: State<List<Message>>,
    isConnect: State<Boolean>
) {
    Column(modifier = modifier) {
        Chat(
            modifier = Modifier.weight(1f).padding(10.dp),
            messages = messages
        )

        HorizontalSeparator()

        SendPanel(
            onSend = onSend,
            isActive = isConnect.value,
            userMessages = messages.value.filter { it.sender == Sender.USER }
        )
    }
}
