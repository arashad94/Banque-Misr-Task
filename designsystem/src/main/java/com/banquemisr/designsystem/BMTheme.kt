package com.banquemisr.designsystem

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

private val LocalCustomColors = staticCompositionLocalOf {
    ThemedColors.defaultColor
}

@Composable
fun BMTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val customColors = ThemedColors.getColorsByMode(isDarkTheme)
    CompositionLocalProvider(
        LocalCustomColors provides customColors,
        content = content
    )
}

@Preview
@Composable
fun PreviewLightTheme() {
    BMTheme(false) {
        Box(
            modifier = Modifier
                .background(color = BMTheme.colors.foreground1)
                .width(Dp(240f))
                .height(Dp(240f))
        ) {
        }
    }
}

@Preview
@Composable
fun PreviewDarkThemeColor() {
    BMTheme(true) {
        Box(
            modifier = Modifier
                .background(color = BMTheme.colors.foreground1)
                .width(Dp(240f))
                .height(Dp(240f))
        ) {
        }
    }
}

object BMTheme {
    val colors: BMColor
        @Composable
        get() = LocalCustomColors.current
}
