package com.ger.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ger.common.addScreen.AddNewRobotScreen
import com.ger.common.data.Robot
import com.ger.common.data.RobotsListProvider
import com.ger.common.kChat.ChatScreen
import com.ger.common.mainScreen.MainScreen
import com.ger.common.nav.Navigation
import com.ger.common.nav.Screens
import com.ger.common.theme.KChatTheme
import com.ger.common.utils.HorizontalSeparator

@Composable
fun App(
    robotsListProvider: RobotsListProvider,
    onAddRobot: (robotName: String, robotIp: String, robotPort: String) -> Unit
) {
    val navigation = remember { Navigation() }
    val index = navigation.indexSelectedRobot
    val robot = robotsListProvider.robots.value[index.value]

    KChatTheme {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize().background(KChatTheme.colors.background)
        ) {
            if (maxWidth > 500.dp) {
                BigScreen(
                    robotsListProvider = robotsListProvider,
                    navigation = navigation,
                    robot = robot,
                    onAddRobot = onAddRobot
                )
            } else {
                SmallScreen(
                    robotsListProvider = robotsListProvider,
                    navigation = navigation,
                    robot = robot,
                    onAddRobot = onAddRobot
                )
            }
        }
    }
}

@Composable
private fun BoxScope.BigScreen(
    modifier: Modifier = Modifier,
    robotsListProvider: RobotsListProvider,
    navigation: Navigation,
    robot: Robot,
    onAddRobot: (robotName: String, robotIp: String, robotPort: String) -> Unit
) {
    HorizontalSeparator(modifier = Modifier.align(Alignment.TopCenter), height = 1.dp)

    Row(modifier) {
        MainScreen(
            modifier = Modifier.width(250.dp),
            addRobot = {
                navigation.move(Screens.ADD_ROBOT)
            },
            onSelectRobot = { position ->
                navigation.chooseRobot(position)
                navigation.move(Screens.CHAT)
                robotsListProvider.robots.value[position].connect()
            },
            compactMode = true,
            robotsListProvider = robotsListProvider,
            selectIndex = navigation.indexSelectedRobot
        )

        when (navigation.state.value) {
            Screens.CHAT -> {
                ChatScreen(
                    modifier = Modifier.weight(1f),
                    onSend = {
                        if (it != "") {
                            robot.sendMessage(it)
                        }
                    },
                    onBack = { navigation.back() },
                    robot = robot,
                )
            }
            Screens.ADD_ROBOT -> {
                AddNewRobotScreen(
                    modifier = Modifier.weight(1f),
                    onBack = { navigation.back() },
                    onAddRobot = onAddRobot
                )
            }
            else -> {
                // TODO: Add initial screen}
            }
        }
    }
}

@Composable
fun SmallScreen(
    modifier: Modifier = Modifier,
    robotsListProvider: RobotsListProvider,
    navigation: Navigation,
    robot: Robot,
    onAddRobot: (robotName: String, robotIp: String, robotPort: String) -> Unit
) {
    when (navigation.state.value) {
        Screens.CHAT -> {
            ChatScreen(
                modifier = modifier,
                onSend = {
                    if (it != "") {
                        robot.sendMessage(it)
                    }
                },
                onBack = {
                    navigation.back()
                },
                robot = robot,
            )
        }
        Screens.ADD_ROBOT -> {
            AddNewRobotScreen(
                modifier = Modifier.fillMaxSize(),
                onBack = { navigation.back() },
                onAddRobot = onAddRobot
            )
        }
        else -> {
            MainScreen(
                modifier = modifier.fillMaxSize(),
                addRobot = {
                    navigation.move(Screens.ADD_ROBOT)
                },
                onSelectRobot = { position ->
                    navigation.chooseRobot(position)
                    navigation.move(Screens.CHAT)
                    robotsListProvider.robots.value[position].connect()
                },
                robotsListProvider = robotsListProvider,
                selectIndex = navigation.indexSelectedRobot
            )
        }
    }
}
