package com.banquemisr.pdpui.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import com.banquemisr.designsystem.BMText.HeadingL
import com.banquemisr.designsystem.BMTextModifier
import com.banquemisr.pdpui.R

@Composable
internal fun PdpErrorScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        HeadingL(
            textModifier = BMTextModifier(
                modifier = Modifier.align(Alignment.Center)
            ),
            text = stringResource(R.string.something_went_wrong) // I prefer using Lokalise to enhance as it delegates the responsibility of localization to the content team
        )
    }
}
