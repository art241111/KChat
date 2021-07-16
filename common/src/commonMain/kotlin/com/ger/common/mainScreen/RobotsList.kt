package com.ger.common.mainScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ger.common.data.RobotsListProvider
import com.ger.common.theme.KChatTheme
import com.ger.common.theme.LightGray

@Composable
fun RobotsList(
    modifier: Modifier = Modifier,
    robotsListProvider: RobotsListProvider,
    onSelectRobot: (Int) -> Unit,
    selectIndex: State<Int>
) {
    val robots = remember() { robotsListProvider.robots }
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        robots.value.forEachIndexed { index, robot ->
            item {
                RobotCard(
                    name = robot.name,
                    ip = robot.ip,
                    port = robot.port,
                    color = robot.color,
                    isActive = index == selectIndex.value,
                    onSelectRobot = {
                        onSelectRobot(index)
                    }
                )
            }
        }
    }
}

@Composable
fun RobotCard(
    modifier: Modifier = Modifier,
    name: String,
    ip: String,
    port: Int,
    color: Color,
    isActive: Boolean,
    onSelectRobot: () -> Unit,

) {
    Card(
        modifier = modifier.fillMaxWidth().clickable { onSelectRobot() },
        border = BorderStroke(width = 0.dp, color = KChatTheme.colors.background),
        elevation = 0.dp,
        shape = RectangleShape,
        backgroundColor = if (isActive) LightGray else MaterialTheme.colors.surface
    ) {
        Row(
            modifier = modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            RobotIcon(
                modifier = Modifier.align(Alignment.CenterVertically),
                name = name,
                size = 50.dp,
                color = color,
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )

                Spacer(modifier.height(3.dp))

                Text(
                    text = "Ip: $ip",
                    fontSize = 14.sp
                )
                Spacer(modifier.height(2.dp))
                Text(
                    text = "Port: $port",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun RobotIcon(
    modifier: Modifier = Modifier,
    name: String,
    size: Dp = 45.dp,
    color: Color,
) {
    val names = name.split(" ")
    val abbreviation = if (names.size < 2) "${names[0].first()}" else "${names[0].first()}${names[1].first()}"

    Card(
        modifier = modifier.size(size),
        shape = CircleShape,
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(color.red, color.green, color.blue, alpha = 0.75f),
                        color
                    )
                )
            )
        ) {

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = abbreviation,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
