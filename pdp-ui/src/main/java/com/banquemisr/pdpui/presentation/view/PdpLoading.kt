package com.banquemisr.pdpui.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.banquemisr.designsystem.shimmerBackground

@Composable
internal fun PdpLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .shimmerBackground()
        )
        Box(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .width(160.dp)
                .height(32.dp)
                .padding(vertical = 4.dp)
                .shimmerBackground()
        )
        Box(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .height(50.dp)
                .shimmerBackground()
        )
    }
}
