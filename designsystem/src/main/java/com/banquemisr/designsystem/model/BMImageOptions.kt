package com.banquemisr.designsystem.model

import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale

data class BMImageOptions(
    val contentScale: ContentScale = ContentScale.Inside,
    val alignment: Alignment = Alignment.Center
)
