package com.example.kotlinjokenpo.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinjokenpo.ui.theme.KotlinJOKENPOTheme
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.foundation.Image
import com.example.kotlinjokenpo.components.IconAndLabelButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.kotlinjokenpo.R

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val image: Painter = painterResource(id = R.drawable.jokenpo)

            Image(
                painter = image,
                contentDescription = "Header",
                modifier = Modifier
                    .fillMaxWidth()
                .padding(bottom = 30.dp),
            contentScale = ContentScale.Crop
            )
            Text(
                text = "Bem-vindo ao Jo-Ken-Po",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7F44D4)
            )
            IconAndLabelButton(buttonLabel = "Single Player",
                iconName = Icons.Default.AccountCircle,
                buttonColor = Color(0xFF7F44D4),
                onClick = {
                    Log.d("button", "Button onSinglePlayerClick Clicked ")
                    /*onSinglePlayerClick,*/
                    navController.navigate("singlePlayer")
                })

            IconAndLabelButton(buttonLabel = "Multi Player",
                iconName = Icons.Default.Face,
                buttonColor = Color(0xFF7F44D4),
                onClick = {
                    Log.d("button", "Button onMultiPlayerClick Clicked ")
                    /*onMultiPlayerClick*/
                    navController.navigate("multiPlayer")
                })
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
