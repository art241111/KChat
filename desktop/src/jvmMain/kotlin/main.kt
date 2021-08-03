import RobotsListProviderImp.Companion.createRoboListProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.ger.common.App
import com.ger.common.nav.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import saveData.FileDataHelper
import saveData.Result

@OptIn(ExperimentalComposeUiApi::class, kotlinx.coroutines.DelicateCoroutinesApi::class)
fun main() = application {
    val robotsListProviderImp = RobotsListProviderImp()
    val navigation = Navigation()

    GlobalScope.launch {
        withContext(Dispatchers.Default) {
            val f: Result = FileDataHelper.getContentAsync("robot.txt")
            when (f) {
                is Result.Success -> createRoboListProvider(f.data, robotsListProviderImp)
                is Result.Error -> createRoboListProvider("", robotsListProviderImp)
            }
        }
    }



    Window(
        onCloseRequest = {
            robotsListProviderImp.disconnectAll()
            GlobalScope.launch(Dispatchers.IO) {
                FileDataHelper.writeContentAsync("robot.txt", robotsListProviderImp.toString().toByteArray())
            }
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
                        port = robotPort.toInt()
                    )
                )
            }
        )
    }
}
