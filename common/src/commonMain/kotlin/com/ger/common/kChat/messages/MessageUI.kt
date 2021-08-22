package com.ger.common.kChat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
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
import java.awt.SystemColor.text

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Message(
    modifier: Modifier = Modifier,
    color: Color,
    onPin: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Card(
        modifier = modifier.padding(5.dp)
            .combinedClickable(
                onLongClick = {
                    onPin()
                }
            ) {},
        backgroundColor = color
    ) {
        Box {
            content()
        }
    }
}

@Composable
internal fun RobotMessage(
    modifier: Modifier = Modifier,
    color: Color = RobotMessageColor,
    onPin: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Message(color = color, content = content, onPin = onPin)
    }
}

@Composable
internal fun UserMessage(
    modifier: Modifier = Modifier,
    color: Color = UserMessageColor,
    onPin: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Message(color = color, content = content, onPin = onPin)
    }
}
