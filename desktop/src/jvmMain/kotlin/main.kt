import androidx.compose.desktop.Window
import androidx.compose.desktop.WindowEvents
import com.ger.common.App

fun main() {
    val robotsListProviderImp = RobotsListProviderImp()

    Window(
        title = "KChat",
        undecorated = false,
        events = WindowEvents(
            onClose = {
                robotsListProviderImp.disconnectAll()
            }
        )
    ) {
        App(
            robotsListProvider = robotsListProviderImp,
            onAddRobot = { robotName, robotIp, robotPort ->
                robotsListProviderImp.addRobot(
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
