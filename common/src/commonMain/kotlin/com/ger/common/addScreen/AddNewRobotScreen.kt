package com.ger.common.addScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ger.common.strings.S

@Composable
fun AddNewRobotScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onAddRobot: (robotName: String, robotIp: String, robotPort: String) -> Unit,
) {
    val robotName = remember { mutableStateOf("") }
    val robotIp = remember { mutableStateOf("192.168.0.1") }
    val robotPort = remember { mutableStateOf("100") }

    val focusManager = LocalFocusManager.current

    val onDone = {
        if (robotName.value.isNotEmpty()) {
            onAddRobot(robotName.value, robotIp.value, robotPort.value)
            onBack()
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(onBack = onBack)
        Spacer(modifier = Modifier.height(6.dp))
        EditText(value = robotName, placeholder = S.strings.addRobotName, focusManager = focusManager)
        Spacer(modifier = Modifier.height(6.dp))
        EditText(value = robotIp, placeholder = S.strings.addRobotIp, focusManager = focusManager)
        Spacer(modifier = Modifier.height(6.dp))
        EditPort(value = robotPort, placeholder = S.strings.addRobotPort, onDone = onDone)
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = onDone,
            enabled = robotName.value.isNotEmpty()
        ) {
            Text(
                S.strings.addNewRobot,
                color = Color.White
            )
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBack,
        ) {
            Icon(Icons.Default.ArrowBack, "Back")
        }

        Spacer(modifier = Modifier.width(3.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = S.strings.addNewRobot,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun EditText(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    placeholder: String,
    focusManager: FocusManager,
) {
    // TODO: Разобраться с фокусом и переходом на сл view
    val isFocus = remember { mutableStateOf(false) }
    TextField(
        modifier = modifier.fillMaxWidth().onFocusEvent {
            isFocus.value = it.isFocused
        }.onPreviewKeyEvent {
            when (it.key) {
                Key.Enter, Key.Tab -> {
                    if (isFocus.value) {
                        focusManager.moveFocus(FocusDirection.Down)
                        isFocus.value = false
                    }

                    true
                }
                else -> false
            }
        },
        value = value.value,
        onValueChange = {
            value.value = it
        },
        singleLine = true,
        placeholder = { Text(text = placeholder) },
        label = { Text(text = placeholder) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}

@Composable
private fun EditPort(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    placeholder: String,
    onDone: () -> Unit,
) {
    val isNumber = remember { mutableStateOf(true) }
    TextField(
        modifier = modifier.fillMaxWidth().onPreviewKeyEvent {
            when (it.key) {
                Key.Enter -> {
                    onDone()
                    true
                }
                else -> false
            }
        },
        value = value.value,
        onValueChange = { newString ->
            isNumber.value = newString.toIntOrNull() != null

            value.value = newString
        },
        isError = !isNumber.value,
        singleLine = true,
        placeholder = { Text(text = placeholder) },
        label = { Text(text = placeholder) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        )
    )
}
