package com.ger.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

actual fun getPlatformName(): String {
    return "Desktop"
}

@Composable
actual fun isInDarkTheme(): Boolean = false
