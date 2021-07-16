package com.ger.common.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ger.common.data.Robot
import com.ger.common.data.RobotsListProvider
import com.ger.common.strings.S
import com.ger.common.theme.KChatTheme
import com.ger.common.theme.LightGray

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
//    openSettings: () -> Unit,
    addRobot: () -> Unit,
    onSelectRobot: (Int) -> Unit,
    compactMode: Boolean = false,
    robotsListProvider: RobotsListProvider,
    selectIndex: State<Int>
) {
    Box(modifier = modifier.fillMaxHeight()) {
        Column {
//
//            HeaderMainScreen(
//                openSettings = openSettings,
//                compactMode = compactMode
//            )
            Spacer(modifier = Modifier.height(5.dp))
//            HorizontalSeparator()

            RobotsList(
                robotsListProvider = robotsListProvider,
                onSelectRobot = onSelectRobot,
                selectIndex = selectIndex
            )
        }

        Button(
            onClick = addRobot,
            modifier = Modifier.align(Alignment.BottomEnd).padding(if (!compactMode) 30.dp else 10.dp).size(50.dp),
            shape = CircleShape
        ) {
            Icon(Icons.Default.Add, "+", tint = Color.White)
        }

        if (compactMode) {
            Box(modifier = Modifier.fillMaxHeight().width((0.8).dp).background(LightGray).align(Alignment.CenterEnd))
        }
    }
}

@Composable
private fun HeaderMainScreen(
    modifier: Modifier = Modifier,
    openSettings: () -> Unit,
    compactMode: Boolean
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .align(if (!compactMode) Alignment.Center else Alignment.CenterStart)
                .padding(start = 10.dp),
            text = S.strings.mainScreenName,
            style = KChatTheme.typography.h1,
            fontSize = if (compactMode) 20.sp else KChatTheme.typography.h1.fontSize
        )

        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = openSettings,
        ) {
            Icon(Icons.Default.Settings, "Settings")
        }
    }
}
