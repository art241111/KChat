package com.ger.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ger.common.App

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val robotsListProvider = RobotsListProviderImp()

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
}
