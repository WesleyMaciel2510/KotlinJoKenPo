package com.example.kotlinjokenpo.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinjokenpo.ui.theme.KotlinJOKENPOTheme

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("HomeScreen", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("play")
        }) {
            Text("Go to PlayScreen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewComponent() {
    val navController = rememberNavController()
    KotlinJOKENPOTheme {
        HomeScreen(navController = navController)
    }
}
