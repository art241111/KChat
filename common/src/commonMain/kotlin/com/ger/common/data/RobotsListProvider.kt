package com.ger.common.data

import androidx.compose.runtime.State

interface RobotsListProvider {
    val robots: State<List<Robot>>
    fun addRobot(robot: Robot)
    fun deleteRobot(robot: Robot)
}
