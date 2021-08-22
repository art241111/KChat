
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender

class MessageImp(
    override val text: String,
    override val sender: Sender,
    override val isPin: MutableState<Boolean> = mutableStateOf(false)
) : Message()
