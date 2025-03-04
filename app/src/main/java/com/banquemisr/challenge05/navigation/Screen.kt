package com.banquemisr.challenge05.navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object PdpScreen : Screen("pdp/{movieId}") {
        fun createRoute(movieId: String) = "pdp/$movieId"
    }
}
