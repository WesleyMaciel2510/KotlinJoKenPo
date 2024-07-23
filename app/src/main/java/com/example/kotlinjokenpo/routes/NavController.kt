package com.example.kotlinjokenpo.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinjokenpo.screens.home.HomeScreen
import com.example.kotlinjokenpo.screens.play.PlayScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController,  modifier = Modifier)
        }
        composable("play/{multiplayer}") { backStackEntry ->
            val multiplayer = backStackEntry.arguments?.getString("multiplayer")?.toBoolean() ?: false
            PlayScreen(navController = navController, modifier = Modifier, multiplayer = multiplayer)
        }
    }
}