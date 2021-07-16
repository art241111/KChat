package com.ger.common.nav

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Stack

class Navigation {
    private val _stack = Stack<Screens>()

    init {
        _stack.push(Screens.MAIN_SCREEN)
    }

    private val _state = mutableStateOf(Screens.MAIN_SCREEN)
    val state: State<Screens> = _state

    private val _indexSelectedRobot = mutableStateOf(0)
    val indexSelectedRobot: State<Int> = _indexSelectedRobot

    fun chooseRobot(index: Int) {
        _indexSelectedRobot.value = index
    }

    fun move(screens: Screens) {
        _state.value = screens
        _stack.push(screens)
    }

    fun back() {
        if (_stack.size > 0) {
            _stack.pop()
            _state.value = _stack.peek()
        }
    }
}
