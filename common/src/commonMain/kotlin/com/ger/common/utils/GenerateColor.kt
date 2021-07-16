package com.ger.common.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun Color.Companion.generateRandomColor(): Color {
    val rnd = Random.Default
    val colorList = listOf(
        0xFF9c14b8,
        0xFFd945a3,
        0xFFd9456a,
        0xFF9645d9,
        0xFF456ad9,
        0xFF45a3d9,
        0xFF45d992,
        0xFF80d945,
        0xFFd9ca45,
        0xFFd95945,

    )
    val colors = colorList[rnd.nextInt(colorList.size)]
    return Color(colors)
}
