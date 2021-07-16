package com.ger.android

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ger.common.data.Robot
import com.ger.common.data.RobotsListProvider

class RobotsListProviderImp : ViewModel(), RobotsListProvider {
    private val _robots: MutableState<List<Robot>> = mutableStateOf(
        mutableListOf(
            RobotImp("Main1", "192.168.56.1", port = 9999),
            RobotImp("Main2", "192.168.0.2", port = 9105),
            RobotImp("Main3", "127.0.0.1", port = 9105),
            RobotImp("Robot 1", "192.168.0.1"),
            RobotImp("Robot 2", "192.168.0.1"),
        )
    )

    override val robots: State<List<Robot>>
        get() = _robots

    override fun addRobot(robot: Robot) {
        val mutableList: MutableList<Robot> = mutableListOf<Robot>()
        mutableList.addAll(_robots.value)

        mutableList.add(robot)
        _robots.value = mutableList
    }

    override fun deleteRobot(robot: Robot) {
        val mutableList: MutableList<Robot> = mutableListOf<Robot>()
        mutableList.addAll(_robots.value)

        mutableList.remove(robot)
        _robots.value = mutableList
    }
}
