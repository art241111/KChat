package com.ger.common.kChat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ger.common.data.Robot
import com.ger.common.mainScreen.RobotIcon
import com.ger.common.utils.HorizontalSeparator

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    onSend: (String) -> Unit,
    onBack: () -> Unit,
    robot: Robot
) {
    Column(modifier) {
        ChatHeader(
            robot = robot,
            onBack = onBack
        )

        HorizontalSeparator()

        KChat(
            modifier = Modifier.weight(1f),
            onSend = onSend,
            messages = robot.chat,
            isConnect = robot.isConnect
        )
    }
}

@Composable
private fun ChatHeader(
    modifier: Modifier = Modifier,
    robot: Robot,
    onBack: () -> Unit
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, "Back")
        }

        RobotIcon(
            modifier = Modifier.align(Alignment.CenterVertically),
            name = robot.name,
            size = 40.dp,
            color = robot.color,
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = robot.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Text(
                text = "${robot.ip}: ${robot.port}",
                fontSize = 14.sp
            )
            Text(
                text = if (robot.isConnect.value) "online" else "offline",
                fontSize = 12.sp
            )
        }
    }
}
