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
    NavHost(navController = navController, startDestination = "play") {
        composable("home") {
            HomeScreen(navController = navController,  modifier = Modifier)
        }
        composable("play") {
            PlayScreen(navController = navController, modifier = Modifier)
        }
    }
}