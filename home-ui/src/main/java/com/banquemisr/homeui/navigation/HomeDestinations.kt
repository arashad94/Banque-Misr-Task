package com.banquemisr.homeui.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.banquemisr.homeui.navigation.HomeDestinations.HOME_ROUTE
import com.banquemisr.homeui.presentation.view.HomeScreen

object HomeDestinations {
    const val HOME_ROUTE = "home"
}

fun NavGraphBuilder.homeNavigation(navController: HomeNavigator) {
    composable(HOME_ROUTE) {
        HomeScreen(navController)
    }
}

interface HomeNavigator {
    fun navigateToHome()
    fun navigateToPdp(id: String)
}
