package com.banquemisr.challenge05.navigation

import androidx.navigation.NavController
import com.banquemisr.homeui.navigation.*
import com.banquemisr.pdpui.presentation.navigation.*

class AppNavigator(
    private val navController: NavController
) : HomeNavigator, PdpNavigator {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToHome() {
        navController.navigate(HomeDestinations.HOME_ROUTE)
    }

    override fun navigateToPdp(id: String) {
        val route = "${PdpDestinations.PDP_ROUTE}/$id"
        navController.navigate(route)
    }
}
