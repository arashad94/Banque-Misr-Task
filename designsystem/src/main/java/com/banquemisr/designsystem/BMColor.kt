package com.banquemisr.designsystem

import androidx.compose.ui.graphics.Color

object ThemedColors {
    val defaultColor: BMColor = Light

    fun getColorsByMode(isDarkMode: Boolean): BMColor {
        return if (isDarkMode) Dark else Light
    }
}

interface BMColor {
    val textPrimary: Color
    val textSecondary: Color
    val foreground1: Color
}

object Light : BMColor {
    override val textPrimary: Color = ColorPalette.black
    override val textSecondary: Color = ColorPalette.grey700
    override val foreground1 = ColorPalette.black
}

object Dark : BMColor {
    override val textPrimary = ColorPalette.grey50
    override val textSecondary = ColorPalette.grey300
    override val foreground1 = ColorPalette.white
}

object ColorPalette {
    val black = Color(0xFF000000)
    val white = Color(0xFFFFFFFF)
    val grey50 = Color(0xFFF5F5F5)
    val grey300 = Color(0xFFBBBCBC)
    val grey700 = Color(0xFF444444)
}