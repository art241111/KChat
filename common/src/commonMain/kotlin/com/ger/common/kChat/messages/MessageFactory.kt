package com.ger.common.kChat.messages

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
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
) {
    when (message.sender) {
        Sender.USER -> {
            UserMessage {
                when {
                    // button<text,action>
                    message.text.contains("button") -> {
                        UserMessageWithButton(message.text, onSendMessage)
                    }
                    else ->{
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
            RobotMessage {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    text = message.text,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun UserMessageWithButton (
    message:String,
    onSendMessage: (String) -> Unit
) {
    val split = message.substringAfter('<').substringBefore('>').split(",")
    Button(
        onClick = {
            onSendMessage(split[1].trim())
        }
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = split[0].trim(),
            color = Color.White
        )
    }
}