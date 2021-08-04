import RobotImp.Companion.getRobotFromString
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ger.common.data.Robot
import com.ger.common.data.RobotsListProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RobotsListProviderImp(private val coroutineScope: CoroutineScope) : RobotsListProvider {
    private val _robots: MutableState<List<Robot>> = mutableStateOf(mutableListOf())

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

    fun disconnectAll() {
        coroutineScope.launch {
            robots.value.forEach {
                it.disconnect()
            }
        }
    }

    override fun toString(): String {
        return _robots.value.joinToString(separator = "\n\n")
    }


    companion object {
        fun createRoboListProvider(
            string: String,
            robotsListProviderImp: RobotsListProviderImp,
            coroutineScope: CoroutineScope
        ) {
            return if (string.isEmpty()) {
                val defaultRobots = listOf(
                    RobotImp("KIDE", "localhost", port = 9105, coroutineScope = coroutineScope),
                    RobotImp("Robot", "192.168.0.2", port = 23, coroutineScope = coroutineScope),
                )

                for (robot in defaultRobots) {
                    robotsListProviderImp.addRobot(robot)
                }
            } else {
                val split = string.split("\n\n")
                for (robotStr in split) {
                    robotsListProviderImp.addRobot(getRobotFromString(robotStr, coroutineScope = coroutineScope))
                }
            }
        }
    }
}
