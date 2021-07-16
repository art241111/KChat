package com.ger.common.kChat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender
import kotlinx.coroutines.launch

@Composable
internal fun Chat(
    modifier: Modifier = Modifier,
    messages: State<List<Message>>
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
                when (it.sender) {
                    Sender.USER -> UserMessage(text = it.text)
                    Sender.ROBOT -> RobotMessage(text = it.text)
                }
            }
        }

        coroutineScope.launch {
            state.scrollToItem(messages.value.size)
        }
    }
}
