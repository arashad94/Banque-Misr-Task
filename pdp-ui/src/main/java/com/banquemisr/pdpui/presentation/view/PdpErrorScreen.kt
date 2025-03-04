package com.banquemisr.pdpui.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import com.banquemisr.designsystem.BMText.HeadingL
import com.banquemisr.designsystem.BMTextModifier

@Composable
internal fun PdpErrorScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        HeadingL(
            textModifier = BMTextModifier(
                modifier = Modifier.align(Alignment.Center)
            ),
            text = "Something went wrong"
        )
    }
}
