package com.ger.common.kChat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ger.common.theme.DarkGray
import com.ger.common.theme.Gray
import com.ger.common.theme.LightGray
import com.ger.common.theme.RobotMessageColor
import com.ger.common.theme.UserMessageColor

@Composable
internal fun Message(
    modifier: Modifier = Modifier,
    color: Color,
    text: String,
) {
    Card(
        modifier = modifier.padding(5.dp),
        backgroundColor = color
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = text,
            color = Color.White
        )
    }
}

@Composable
internal fun RobotMessage(
    modifier: Modifier = Modifier,
    color: Color = RobotMessageColor,
    text: String,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Message(color = color, text = text)
    }
}

@Composable
internal fun UserMessage(
    modifier: Modifier = Modifier,
    color: Color = UserMessageColor,
    text: String,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Message(color = color, text = text)
    }
}
