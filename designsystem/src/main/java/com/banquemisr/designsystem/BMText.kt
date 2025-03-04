package com.banquemisr.designsystem

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.TextStyle

object BMText {
    @Composable
    fun HeadingL(textModifier: BMTextModifier = BMTextModifier(), text: String) {
        BasicText(
            text = text.uppercase(),
            bmTextModifier = textModifier,
            style = BMTypography.HeadingL,
            defaultColor = BMTheme.colors.textPrimary
        )
    }

    @Composable
    fun HeadingM(textModifier: BMTextModifier = BMTextModifier(), text: String) {
        BasicText(
            text = text,
            bmTextModifier = textModifier,
            style = BMTypography.HeadingM,
            defaultColor = BMTheme.colors.textSecondary
        )
    }

    @Composable
    private fun BasicText(text: String, bmTextModifier: BMTextModifier, style: TextStyle, defaultColor: Color) {
        Text(
            text = text,
            modifier = bmTextModifier.modifier.semantics {
                if (bmTextModifier.contentDescription.isNotBlank()) {
                    contentDescription = bmTextModifier.contentDescription
                }
            },
            style = style,
            color = bmTextModifier.color ?: defaultColor,
            maxLines = bmTextModifier.maxLines,
            minLines = bmTextModifier.minLines,
            overflow = bmTextModifier.overflow,
            textAlign = bmTextModifier.textAlign
        )
    }
}
