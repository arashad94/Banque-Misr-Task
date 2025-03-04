package com.banquemisr.pdpui.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.banquemisr.designsystem.HeaderBar
import com.banquemisr.pdpui.presentation.navigation.PdpNavigator
import com.banquemisr.pdpui.presentation.viewmodel.PdpViewModel

@Composable
fun PdpScreen(navigator: PdpNavigator, movieId: String) {
    val viewModel: PdpViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }

    viewModel.fetchMovieDetails(movieId)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderBar(title = "", onBackClicked = onBackClicked)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (state) {
                PdpViewModel.State.Idle -> {}
                PdpViewModel.State.Loading -> {
                    PdpLoading()
                }

                PdpViewModel.State.Error -> { PdpErrorScreen() }
                is PdpViewModel.State.Content -> {
                    val content = state as PdpViewModel.State.Content
                    PdpContent(content.movie)
                }
            }
        }
    }
}
