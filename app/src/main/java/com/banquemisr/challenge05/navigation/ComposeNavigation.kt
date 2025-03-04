package com.banquemisr.challenge05.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.banquemisr.homeui.navigation.homeNavigation
import com.banquemisr.pdpui.presentation.navigation.pdpNavigation

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    val appNavigator = AppNavigator(navController)
    NavHost(navController = navController, startDestination = "home") {
        homeNavigation(appNavigator)
        pdpNavigation(appNavigator)
    }
}
