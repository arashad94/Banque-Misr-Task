package com.banquemisr.designsystem

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.*

data class BMTextModifier(
    val modifier: Modifier = Modifier,
    val maxLines: Int = Int.MAX_VALUE,
    val minLines: Int = 1,
    val overflow: TextOverflow = TextOverflow.Clip,
    val color: Color? = null,
    val textAlign: TextAlign? = null,
    val contentDescription: String = ""
)
