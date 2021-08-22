package com.ger.common.kChat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender
import com.ger.common.kChat.messages.MessageFactory
import kotlinx.coroutines.launch
import java.awt.SystemColor.text

@Composable
internal fun Chat(
    modifier: Modifier = Modifier,
    messages: State<List<Message>>,
    onSendMessage: (String) -> Unit
) {
    // Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()

    val state = rememberLazyListState(messages.value.size)
    LazyColumn(
        state = state,
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        messages.value.forEach {
            item(key = it) {
                MessageFactory(it, onSendMessage)
            }
        }

        coroutineScope.launch {
            state.scrollToItem(messages.value.size)
        }
    }
}

