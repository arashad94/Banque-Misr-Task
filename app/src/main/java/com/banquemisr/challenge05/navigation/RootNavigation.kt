package com.banquemisr.challenge05.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.banquemisr.homeui.presentation.view.HomeScreen
import com.banquemisr.pdpui.presentation.view.PdpScreen

@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.PdpScreen.route) { entry ->
            val movieId = entry.arguments?.getString("movieId")
            requireNotNull(movieId) { "Movie ID is required" }
            PdpScreen(movieId)
        }
    }
}
