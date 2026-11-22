package com.unicode.stationsapp.core.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.unicode.stationsapp.stations.presentation.screen.StationDetailScreen
import com.unicode.stationsapp.stations.presentation.screen.StationsScreen

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    MainAppRouter(navController = navController)
}

@Composable
fun MainAppRouter(navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { screenPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Stations,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            modifier = Modifier.fillMaxSize()
        ) {
            composable<Routes.Stations> {
                StationsScreen(
                    screenPadding = screenPadding,
                    navController = navController
                )
            }
            composable<Routes.StationDetail> { backStackEntry ->
                val args = backStackEntry.toRoute<Routes.StationDetail>()
                StationDetailScreen(
                    screenPadding = screenPadding,
                    stationId = args.id,
                    navController = navController
                )
            }
        }
    }
}