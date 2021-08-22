package com.ger.common.kChat.messages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ger.common.kChat.RobotMessage
import com.ger.common.kChat.UserMessage
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender

@Composable
internal fun MessageFactory(
    message: Message,
    onSendMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val onPin = { message.isPin.value = true }
    when (message.sender) {
        Sender.USER -> {
            UserMessage(
                modifier = modifier,
                onPin = onPin
            ) {
                when {
                    // button<text,action>
                    message.text.contains("button") -> {
                        UserMessageWithButton(message.text, onSendMessage, onPin)
                    }
                    else -> {
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            text = message.text,
                            color = Color.White
                        )
                    }
                }
            }
        }
        Sender.ROBOT -> {
            RobotMessage(
                modifier = modifier,
                onPin = onPin
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    text = message.text,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserMessageWithButton(
    message: String,
    onSendMessage: (String) -> Unit,
    onPin: () -> Unit
) {
    val split = message.substringAfter('<').substringBefore('>').split(",")
    Surface(
        modifier = Modifier.combinedClickable(onLongClick = onPin) {
            onSendMessage(split[1].trim())
        },
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = split[0].trim(),
            color = Color.White
        )
    }
}