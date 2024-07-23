package com.example.kotlinjokenpo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.kotlinjokenpo.routes.AppNavigation
import com.example.kotlinjokenpo.services.firebase.checkConnection
import com.example.kotlinjokenpo.ui.theme.DarkColorScheme
import com.example.kotlinjokenpo.ui.theme.KotlinJOKENPOTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Firebase Startup
        FirebaseApp.initializeApp(this)
        checkConnection()

        enableEdgeToEdge()

        // Set navigation bar color to black
        window.navigationBarColor = android.graphics.Color.BLACK

        // Optionally, set light navigation bar icons if the navigation bar is dark
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = false

        setContent {
            KotlinJOKENPOTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    App()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold(
        // add here what is fixed
        topBar = {
            TopAppBar(
                title = { Text("Jo-Ken-Po") },
                Modifier.background(Color(0xFF7F44D4)),
                //contentColor = Color.White
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = DarkColorScheme.background)
        ) {
            AppNavigation(navController = navController, modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPage() {
    KotlinJOKENPOTheme {
        App()
    }
}