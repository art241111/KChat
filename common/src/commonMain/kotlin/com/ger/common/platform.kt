package com.ger.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.ger.common.data.Robot

expect fun getPlatformName(): String

@Composable
expect fun isInDarkTheme(): Boolean