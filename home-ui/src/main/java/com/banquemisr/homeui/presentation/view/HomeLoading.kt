package com.banquemisr.homeui.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.banquemisr.designsystem.shimmerBackground

@Composable
internal fun HomeLoading() {
    LazyRow(modifier = Modifier.fillMaxSize()) {
        repeat(3) {
            item {
                Column(modifier = Modifier.wrapContentSize().padding(8.dp)) {
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(200.dp)
                            .shimmerBackground()
                    )
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(32.dp)
                            .padding(vertical = 4.dp)
                            .shimmerBackground()
                    )
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(40.dp)
                            .shimmerBackground()
                    )
                }
            }
        }
    }
}
