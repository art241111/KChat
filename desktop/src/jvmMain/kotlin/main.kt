import RobotsListProviderImp.Companion.createRoboListProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.ger.common.App
import com.ger.common.nav.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import saveData.FileDataHelper
import saveData.Result

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    val coroutineScope = rememberCoroutineScope()

    val robotsListProviderImp = RobotsListProviderImp(coroutineScope)
    val navigation = Navigation()

    coroutineScope.launch {
        withContext(Dispatchers.Default) {
            when (val f: Result = FileDataHelper.getContentAsync("robot.data")) {
                is Result.Success -> createRoboListProvider(f.data, robotsListProviderImp, coroutineScope)
                is Result.Error -> createRoboListProvider("", robotsListProviderImp, coroutineScope)
            }
        }
    }

    Window(
        icon = painterResource("robot.png"),
        onCloseRequest = {
            robotsListProviderImp.disconnectAll()
            coroutineScope.launch(Dispatchers.IO) {
                FileDataHelper.writeContentAsync("robot.data", robotsListProviderImp.toString().toByteArray())
            }
            coroutineScope.cancel()
            this.exitApplication()

        },
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
                        port = robotPort.toInt(),
                        coroutineScope = coroutineScope
                    )
                )
            }
        )
    }
}
