package com.banquemisr.pdpui.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.banquemisr.pdpui.presentation.navigation.PdpDestinations.MOVIE_ID_ARG
import com.banquemisr.pdpui.presentation.navigation.PdpDestinations.PDP_ROUTE
import com.banquemisr.pdpui.presentation.view.PdpScreen

object PdpDestinations {
    const val PDP_ROUTE = "pdp/{movieId}"
    const val MOVIE_ID_ARG = "userId"
}

fun NavGraphBuilder.pdpNavigation(navigator: PdpNavigator) {
    composable(
        route = "$PDP_ROUTE/{$MOVIE_ID_ARG}",
        arguments = listOf(
            navArgument(MOVIE_ID_ARG) { type = NavType.StringType }
        )
    ) { entry ->
        val id = entry.arguments?.getString(MOVIE_ID_ARG)
        requireNotNull(id) { "Movie ID is required." }
        PdpScreen(navigator, id)
    }
}

interface PdpNavigator {
    fun navigateToPdp(id: String)
    fun navigateUp()
}
