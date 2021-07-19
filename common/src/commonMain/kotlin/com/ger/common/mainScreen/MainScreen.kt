package com.ger.common.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ger.common.data.Robot
import com.ger.common.data.RobotsListProvider
import com.ger.common.theme.LightGray

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    addRobot: () -> Unit,
    onSelectRobot: (Int) -> Unit,
    compactMode: Boolean = false,
    robotsListProvider: RobotsListProvider,
    selectIndex: State<Int>,
    deleteRobot: (Robot) -> Unit,
) {
    Box(modifier = modifier.fillMaxHeight()) {
        Column {
            Spacer(modifier = Modifier.height(5.dp))

            RobotsList(
                robotsListProvider = robotsListProvider,
                onSelectRobot = onSelectRobot,
                selectIndex = selectIndex,
                deleteRobot = deleteRobot
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
