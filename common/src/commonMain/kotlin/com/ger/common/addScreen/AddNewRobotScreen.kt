package com.ger.common.addScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
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

    Column(modifier) {
        Header(onBack = onBack)
        Spacer(modifier = Modifier.height(10.dp))
        EditText(value = robotName, placeholder = S.strings.addRobotName)
        Spacer(modifier = Modifier.height(10.dp))
        EditText(value = robotIp, placeholder = S.strings.addRobotIp)
        Spacer(modifier = Modifier.height(10.dp))
        EditText(value = robotPort, placeholder = S.strings.addRobotPort)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onAddRobot(robotName.value, robotIp.value, robotPort.value)
                onBack()
            }
        ) {
            Text(S.strings.addNewRobot)
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Row(modifier) {
        IconButton(
            onClick = onBack,
        ) {
            Icon(Icons.Default.ArrowBack, "Back")
        }

        Spacer(modifier = Modifier.width(3.dp))

        Text(S.strings.back)

        Text(
            modifier = Modifier.weight(1f),
            text = S.strings.addNewRobot
        )
    }
}

@Composable
private fun EditText(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    placeholder: String,
) {
    TextField(
        modifier = modifier,
        value = value.value,
        onValueChange = {
            value.value = it
        },
        singleLine = true,
        placeholder = { Text(text = placeholder) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
    )
}
