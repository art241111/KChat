import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import androidx.compose.desktop.WindowEvents
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.ger.common.App
import com.ger.common.nav.Navigation

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    val robotsListProviderImp = RobotsListProviderImp()
    val navigation = Navigation()

    androidx.compose.ui.window.Window(
        onCloseRequest = ::exitApplication,
        title = "KChat",
        state = rememberWindowState(width = 1000.dp, height = 800.dp),
        onKeyEvent = {
            when (it.key) {
                Key.Escape -> {
                    navigation.back()
                    true
                }
                else -> false
            }
        }
    ) {
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
