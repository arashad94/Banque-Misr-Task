package com.banquemisr.homeui.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.banquemisr.homeui.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onTabSelected: (Int) -> Unit = { viewModel.onTabSelected(it) }
    val onMovieClicked: (String) -> Unit = { navController.navigate("pdp/$it") }

    viewModel.fetchMovies()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (state) {
                HomeViewModel.State.Idle -> {}
                is HomeViewModel.State.Content -> {
                    val content = state as HomeViewModel.State.Content
                    ScreenContent(content, onTabSelected, onMovieClicked)
                }
            }
        }
    }
}

@Composable
internal fun ScreenContent(
    content: HomeViewModel.State.Content,
    onTabSelected: (Int) -> Unit,
    onMovieClicked: (String) -> Unit
) {
    val selectedTabIndex by remember(content.index) { mutableIntStateOf(content.index) }
    TabRow(selectedTabIndex = selectedTabIndex) {
        content.tabsList.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.title) },
                selected = content.index == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
    when (content.displayState) {
        is HomeViewModel.DisplayState.Loading -> {
            HomeLoading()
        }

        is HomeViewModel.DisplayState.Error -> {
            ErrorScreen()
        }

        is HomeViewModel.DisplayState.ContentState -> {
            val movies = content.displayState.movies
            PlpScreen(movies, onMovieClicked)
        }
    }
}
