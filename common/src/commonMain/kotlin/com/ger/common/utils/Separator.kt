package com.ger.common.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ger.common.theme.LightGray

@Composable
fun HorizontalSeparator(
    modifier: Modifier = Modifier,
    color: Color = LightGray,
    height: Dp = (0.8).dp
) {
    Box(modifier = modifier.background(color).fillMaxWidth().height(height))
}

@Composable
fun VerticalSeparator(
    modifier: Modifier = Modifier,
    color: Color = LightGray,
    width: Dp = (0.8).dp
) {
    Box(modifier = modifier.background(color).fillMaxHeight().width(width))
}
