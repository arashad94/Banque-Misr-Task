package com.banquemisr.challenge05

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.banquemisr.homeui.presentation.view.HomeScreen

@Composable
fun BMComposeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
    }
}
