import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.ger.common.data.Robot
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender
import com.ger.common.utils.generateRandomColor
import com.github.art241111.tcpClient.Client
import com.github.art241111.tcpClient.connection.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RobotImp(
    override val name: String = "",
    override val ip: String = "",
    override val port: Int = 0,
    override val color: Color = Color.generateRandomColor(),
) : Robot {
    private val _isConnect = mutableStateOf(false)
    override val isConnect: State<Boolean> = _isConnect

    private val client = Client()

    override fun disconnect() {
        client.disconnect(stopSymbol = "q")
    }

    init {
        if (port != 0 && ip != "") {
            GlobalScope.launch(Dispatchers.IO) {
                println(name)

                client.connect(
                    ip,
                    port
                )

                GlobalScope.launch(Dispatchers.IO) {
                    client.statusState.collect {
                        _isConnect.value = it == Status.COMPLETED
                    }
                }

                GlobalScope.launch(Dispatchers.IO) {
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
            GlobalScope.launch(Dispatchers.IO) {
                client.connect(ip, port)
            }
        }
    }

    private val _chat = mutableStateOf(
        mutableListOf<Message>(
            MessageImp("Hello, User", sender = Sender.ROBOT),
            MessageImp("Hello, $name", sender = Sender.USER)
        )
    )
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
}
