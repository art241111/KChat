package com.ger.common.kChat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ger.common.kChat.data.Message
import com.ger.common.strings.S

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun SendPanel(
    modifier: Modifier = Modifier,
    defaultValue: String = "",
    onSend: (String) -> Unit,
    userMessages: List<Message>,
    isActive: Boolean
) {
    var txt by remember { mutableStateOf(defaultValue) }
    var userMessageIndex by remember { mutableStateOf(-1.5) }

    LaunchedEffect(userMessageIndex) {
        if (userMessageIndex > -1 && userMessages.lastIndex >= userMessageIndex) {
            txt = userMessages[userMessages.lastIndex - userMessageIndex.toInt()].text
        } else if (userMessageIndex < -1) {
            txt = ""
            userMessageIndex = -1.0
        }
    }
    Row(
        modifier = modifier.padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
//            enabled = isActive,
            modifier = Modifier.weight(1f).onPreviewKeyEvent {
                when (it.key) {
                    Key.Enter -> {
                        onSend(txt)
                        txt = ""
                        true
                    }
                    Key.DirectionUp -> {
                        userMessageIndex += 0.5
                        true
                    }
                    Key.DirectionDown -> {
                        userMessageIndex--
                        true
                    }
                    else -> false
                }
            },
            value = txt,
            onValueChange = {
                txt = it
            },
            singleLine = true,
            placeholder = { Text(text = S.strings.message) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSend(txt)
                    txt = ""
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(10.dp))

        IconButton(
            onClick = {
                onSend(txt)
                txt = ""
            }
        ) {
            Icon(Icons.Default.Send, "Send")
        }
    }
}
