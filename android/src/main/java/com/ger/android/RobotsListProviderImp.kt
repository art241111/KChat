package com.ger.android

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ger.common.data.Robot
import com.ger.common.data.RobotsListProvider

class RobotsListProviderImp() : ViewModel(), RobotsListProvider {
    private val _robots: MutableState<List<Robot>> = mutableStateOf(
        mutableListOf(
            RobotImp("Main1", "192.168.56.1", port = 9999),
            RobotImp("Main2", "192.168.0.2", port = 9105),
            RobotImp("Main3", "127.0.0.1", port = 9105),
            RobotImp("Robot 1", "192.168.0.1"),
            RobotImp("Robot 2", "192.168.0.1"),
        )
    )

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var SP_ROBOT_LIST: String

    override val robots: State<List<Robot>>
        get() = _robots

    @SuppressLint("CommitPrefEdits")
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

    /**
     * Load robot list to shared preferences
     */
    fun loadToSP() {
        val editor = sharedPreferences.edit()

        val robotListStr = _robots.value.joinToString("\n")

        editor.putString(SP_ROBOT_LIST, robotListStr)
        editor.apply()
    }

    fun loadDefaultData(sharedPreferences: SharedPreferences, spRobotList: String) {
        this.sharedPreferences = sharedPreferences
        SP_ROBOT_LIST = spRobotList

        val robotListStr: String? = sharedPreferences.getString(SP_ROBOT_LIST, "")

        if (robotListStr?.isNotEmpty() == true) {
            val robotList = mutableListOf<RobotImp>()
            val split = robotListStr.split("\n\n")
            split.forEach {
                robotList.add(RobotImp.getFromString(it))
            }
            _robots.value = robotList
        }
    }
}
