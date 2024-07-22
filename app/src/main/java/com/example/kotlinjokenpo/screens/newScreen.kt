package com.example.kotlinjokenpo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinjokenpo.ui.theme.KotlinJOKENPOTheme

@Composable
fun NewScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
    Text("Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewComponent() {
    val navController = rememberNavController()
    KotlinJOKENPOTheme {
        NewScreen(navController = navController)
    }
}
