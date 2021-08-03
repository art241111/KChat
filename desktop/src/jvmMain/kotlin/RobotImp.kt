import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.ger.common.data.Robot
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender
import com.ger.common.utils.generateRandomColor
import com.github.art241111.tcpClient.Client
import com.github.art241111.tcpClient.connection.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RobotImp(
    override val name: String = "",
    override val ip: String = "",
    override val port: Int = 0,
    override val color: Color = Color.generateRandomColor(),
    val coroutineScope: CoroutineScope
) : Robot() {
    private val _isConnect = mutableStateOf(false)
    override val isConnect: State<Boolean> = _isConnect

    private val client = Client()

    override fun disconnect() {
        client.disconnect(stopSymbol = "q")
    }

    init {
        if (port != 0 && ip != "") {
            coroutineScope.launch(Dispatchers.IO) {
                println(name)

                client.connect(
                    ip,
                    port
                )

                coroutineScope.launch(Dispatchers.IO) {
                    client.statusState.collect {
                        _isConnect.value = it == Status.COMPLETED
                    }
                }

                coroutineScope.launch(Dispatchers.IO) {
                    client.incomingText.collect { message ->
                        val mutableChat = mutableListOf<Message>()
                        mutableChat.addAll(_chat.value)

                        mutableChat.add(MessageImp(message, Sender.ROBOT))

                        _chat.value = mutableChat
                    }
                }
            }
        }
    }

    override fun connect() {
        if (client.statusState.value != Status.COMPLETED && client.statusState.value != Status.CONNECTING) {
            coroutineScope.launch(Dispatchers.IO) {
                client.connect(ip, port)
            }
        }
    }

    private val _chat = mutableStateOf(mutableListOf<Message>())
    override val chat: State<List<Message>> = _chat

    override fun sendMessage(message: String) {
        val mutableChat = mutableListOf<Message>()
        mutableChat.addAll(_chat.value)

        mutableChat.add(MessageImp(message, Sender.USER))

        _chat.value = mutableChat

        if (client.statusState.value == Status.COMPLETED) {
            client.send(message)
        }
    }

    fun addMessages(messages: List<Message>) {
        _chat.value.addAll(messages)
    }

    companion object {
        fun getRobotFromString(string: String, coroutineScope: CoroutineScope): Robot {
            val split = string.split("\n")
            val name = split[0].trim()
            val ip = split[1].trim()
            val port = split[2].trim()
            val color = split[3].trim().split(";")

            val robot = RobotImp(
                name = name,
                ip = ip,
                port = port.toInt(),
                color = Color(color[0].toFloat(), color[1].toFloat(), color[2].toFloat()),
                coroutineScope = coroutineScope
            )
            val messages = mutableListOf<Message>()
            for (i in 4..split.lastIndex) {
                val messageSplit = split[i].split(":")
                messages.add(
                    MessageImp(
                        sender = Sender.values()[messageSplit[0].toInt()],
                        text = messageSplit[1].trim()
                    )
                )
            }
            robot.addMessages(messages)

            return robot
        }
    }
}
