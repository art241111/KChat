package com.ger.android

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ger.common.App

class MainActivity : AppCompatActivity() {
    private val APP_PREFERENCES = "KCHAT"
    private val SP_ROBOT_LIST = "ROBOT_LIST"
    private val SP_CHAT = "CHAT"

    private val robotsListProvider = RobotsListProviderImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        robotsListProvider.loadDefaultData(sharedPreferences, SP_ROBOT_LIST)

        setContent {
            App(
                robotsListProvider = robotsListProvider,
                onAddRobot = { robotName, robotIp, robotPort ->
                    robotsListProvider.addRobot(
                        RobotImp(
                            name = robotName,
                            ip = robotIp,
                            port = robotPort.toInt()
                        )
                    )
                }
            )
        }
    }

    override fun onPause() {
        robotsListProvider.loadToSP()
        super.onPause()
    }
}
