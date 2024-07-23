package com.example.kotlinjokenpo.screens.play

import LottieAnimationView
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinjokenpo.R
import com.example.kotlinjokenpo.components.IconAndLabelButton
import com.example.kotlinjokenpo.data.JokenPoGame
//import com.example.kotlinjokenpo.services.firebase.FirestoreRepository
import com.example.kotlinjokenpo.ui.theme.KotlinJOKENPOTheme
import com.example.kotlinjokenpo.utils.CountdownTimer
import com.example.kotlinjokenpo.utils.determineWinner
import com.example.kotlinjokenpo.utils.generateRandomChoice

@Composable
fun PlayScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val gameId = "1211"
    var game by remember { mutableStateOf(JokenPoGame(gameId = gameId)) }
    var ready by remember { mutableStateOf(false) }
    var gameFinished by remember { mutableStateOf(false) }
    var countdown by remember { mutableStateOf(6) }
    var playerChoice by remember { mutableStateOf(-1) } // -1 indicates no choice made
    var player2Choice by remember { mutableStateOf(generateRandomChoice()) }
    var resultMessage by remember { mutableStateOf("") }

    DisposableEffect(gameId) {
        val listenerRegistration = setupGame(gameId) { updatedGame ->
            if (updatedGame != null) {
                game = updatedGame
            }
        }
        onDispose {
            listenerRegistration.remove()
        }
    }

    var countdownFinished by remember { mutableStateOf(false) }

    if (ready && countdown > 0 && !gameFinished) {
        CountdownTimer(
            countdownState = remember { mutableIntStateOf(countdown) },
            onCountdownUpdate = { updatedCountdown ->
                countdown = updatedCountdown
                if (updatedCountdown == 0) {
                    countdownFinished = true
                }
            }
        )
    }

    if (countdownFinished && !gameFinished) {
        // This block will only be called once when the countdown reaches zero
        gameFinished = true
        resultMessage = determineWinner(playerChoice, player2Choice)
        Log.d("PlayScreen", "Countdown finished. Player Choice: $playerChoice, Player 2 Choice: $player2Choice, Result: $resultMessage")
    }

    // UI AREA ===============================================================================
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            val image: Painter = painterResource(id = R.drawable.jokenpo)

            if (!ready) {
                Image(
                    painter = image,
                    contentDescription = "Header",
                    modifier = Modifier
                        .width(300.dp)
                        .padding(bottom = 30.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                LottieAnimationView(
                    animationName = R.raw.countdown,
                    looping = false,
                    isPlaying = (countdown > 0),
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Player 1",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                textAlign = TextAlign.Center
            )

            if (ready) {
                Text(
                    text = when (playerChoice) {
                        0 -> "You chose Rock"
                        1 -> "You chose Paper"
                        2 -> "You chose Scissors"
                        else -> "Make a choice"
                    },
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }

            if (ready && !gameFinished) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconAndLabelButton(
                        buttonLabel = "Rock",
                        iconName = Icons.Default.AccountCircle,
                        buttonColor = Color(0xFF03A9F4),
                        onClick = {
                            if (countdown > 0) {
                                playerChoice = 0
                            }
                            Log.d("PlayScreen", "BUTTON PRESSED: playerChoice = $playerChoice")
                        }
                    )
                    IconAndLabelButton(
                        buttonLabel = "Paper",
                        iconName = Icons.Default.AccountCircle,
                        buttonColor = Color(0xFFF7D119),
                        onClick = {
                            if (countdown > 0) {
                                playerChoice = 1
                            }
                            Log.d("PlayScreen", "BUTTON PRESSED: playerChoice = $playerChoice")
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconAndLabelButton(
                        buttonLabel = "Scissors",
                        iconName = Icons.Default.AccountCircle,
                        buttonColor = Color(0xFFFF6F6F),
                        onClick = {
                            if (countdown > 0) {
                                playerChoice = 2
                            }
                            Log.d("PlayScreen", "BUTTON PRESSED: playerChoice = $playerChoice")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!ready || gameFinished) {
                IconAndLabelButton(
                    buttonLabel = if (gameFinished) "PLAY AGAIN" else "READY",
                    iconName = Icons.Default.PlayArrow,
                    buttonColor = Color(0xFF4CAF50),
                    onClick = {
                        if (gameFinished) {
                            gameFinished = false
                            ready = false
                            countdown = 6
                            playerChoice = -1
                            player2Choice = generateRandomChoice()
                            resultMessage = ""
                            countdownFinished = false
                            // Reset game state or navigate to a new game
                        } else {
                            ready = true
                            // Logic for "READY" button, such as updating game state in Firestore
                        }
                    }
                )
            }

            if (gameFinished) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = resultMessage,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
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