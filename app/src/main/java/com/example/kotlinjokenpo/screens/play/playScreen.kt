package com.example.kotlinjokenpo.screens.play

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinjokenpo.components.IconAndLabelButton
import com.example.kotlinjokenpo.ui.theme.KotlinJOKENPOTheme
import com.example.kotlinjokenpo.ui.theme.LightColorScheme

@Composable
fun PlayScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("PlayScreen", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        IconAndLabelButton(
            //iconName = Icons.Default.AddCircle,
            buttonLabel = "BUY TICKETS",
            buttonColor = LightColorScheme.secondary,
            onClick = {
                navController.navigate("home")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewComponent() {
    val navController = rememberNavController()

    KotlinJOKENPOTheme {
        PlayScreen(navController = navController)
    }
}