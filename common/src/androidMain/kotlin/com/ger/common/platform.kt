package com.ger.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

actual fun getPlatformName(): String {
    return "Android"
}

@Composable
actual fun isInDarkTheme(): Boolean = isSystemInDarkTheme()
