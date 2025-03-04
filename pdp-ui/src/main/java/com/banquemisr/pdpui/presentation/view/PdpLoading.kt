package com.banquemisr.pdpui.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.banquemisr.designsystem.*

@Composable
internal fun PdpLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BMSpacing.nano)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .shimmerBackground()
        )
        Box(
            modifier = Modifier
                .padding(top = BMSpacing.xxsm, bottom = BMSpacing.nano)
                .width(160.dp)
                .height(BMSpacing.sm)
                .padding(vertical = BMSpacing.quark)
                .shimmerBackground()
        )
        Box(
            modifier = Modifier
                .padding(vertical = BMSpacing.nano)
                .fillMaxWidth()
                .height(50.dp)
                .shimmerBackground()
        )
    }
}
