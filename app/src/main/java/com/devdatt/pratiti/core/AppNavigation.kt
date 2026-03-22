package com.devdatt.pratiti.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devdatt.pratiti.feature.category.CategoryScreen
import com.devdatt.pratiti.feature.home.HomeScreen
import com.devdatt.pratiti.feature.player.PlayerScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("category/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            CategoryScreen(name ?: "", navController)
        }

        composable("player/{trackId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("trackId")?.toInt() ?: 0
            PlayerScreen(id)
        }
    }
}
