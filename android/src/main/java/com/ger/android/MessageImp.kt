package com.ger.android

import android.os.Parcelable
import com.ger.common.kChat.data.Message
import com.ger.common.kChat.data.Sender
import kotlinx.android.parcel.Parcelize

@Parcelize
class MessageImp(
    override val sender: Sender,
    override val text: String,
) : Parcelable, Message {
    override fun toString(): String {
        return "$sender:$text"
    }
}
