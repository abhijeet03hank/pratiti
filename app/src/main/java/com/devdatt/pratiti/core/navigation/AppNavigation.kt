package com.devdatt.pratiti.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devdatt.pratiti.feature.category.CategoryScreen
import com.devdatt.pratiti.feature.home.HomeScreen
import com.devdatt.pratiti.feature.player.PlayerScreen

/**
 * Central place for navigation route names and helpers.
 *
 * Prefer [category] / [player] helpers so screens never hand-build path strings.
 */
object Routes {
    const val HOME = "home"
    const val CATEGORY = "category/{categoryId}"
    const val PLAYER = "player/{trackId}"

    fun category(categoryId: String) = "category/$categoryId"
    fun player(trackId: Int) = "player/$trackId"
}

/**
 * Root Compose navigation graph: Home → Category → Player.
 *
 * Bottom-tab switching on Home is local UI state only (not separate Nav destinations yet).
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(
            route = Routes.CATEGORY,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId").orEmpty()
            CategoryScreen(categoryId = categoryId, navController = navController)
        }
        composable(
            route = Routes.PLAYER,
            arguments = listOf(navArgument("trackId") { type = NavType.IntType })
        ) {
            PlayerScreen(navController = navController)
        }
    }
}
