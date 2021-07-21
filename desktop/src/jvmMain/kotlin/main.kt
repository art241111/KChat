import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import androidx.compose.desktop.WindowEvents
import androidx.compose.ui.input.key.Key
import com.ger.common.App
import com.ger.common.nav.Navigation

fun main() {
    val robotsListProviderImp = RobotsListProviderImp()
    val navigation = Navigation()
    Window(
        title = "KChat",
        undecorated = false,
        events = WindowEvents(
            onClose = {
                robotsListProviderImp.disconnectAll()
            }
        )
    ) {
        LocalAppWindow.current.keyboard.setShortcut(
            key = Key.Escape,
            callback = {
                navigation.back()
            }
        )

        App(
            navigation = navigation,
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
