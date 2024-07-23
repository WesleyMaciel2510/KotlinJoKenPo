package com.example.kotlinjokenpo.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinjokenpo.screens.home.HomeScreen
import com.example.kotlinjokenpo.screens.singlePlayer.SinglePlayerScreen
import com.example.kotlinjokenpo.screens.multiPlayer.MultiPlayerScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController,  modifier = Modifier)
        }
        composable("singlePlayer") {
            SinglePlayerScreen(navController = navController, modifier = Modifier)
        }
        composable("multiPlayer") {
            MultiPlayerScreen(navController = navController, modifier = Modifier)
        }
    }
}