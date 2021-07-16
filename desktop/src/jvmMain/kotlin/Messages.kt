
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender

class MessageImp(
    override val text: String,
    override val sender: Sender
) : Message
