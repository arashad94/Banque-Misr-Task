package com.banquemisr.pdpui.presentation.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.banquemisr.pdpui.presentation.navigation.PdpNavigator

@Composable
fun PdpScreen(navigator: PdpNavigator, movieId: String) {
    Text("Hello in PDP $movieId")
}
