package com.example.kotlinjokenpo.screens.multiPlayer

import LottieAnimationView
import android.graphics.drawable.Icon
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinjokenpo.R
import com.example.kotlinjokenpo.components.IconAndLabelButton
import com.example.kotlinjokenpo.services.firebase.addNewRoom
import com.example.kotlinjokenpo.services.firebase.searchRoom
import com.example.kotlinjokenpo.ui.theme.KotlinJOKENPOTheme
import com.example.kotlinjokenpo.utils.CountdownTimer
import com.example.kotlinjokenpo.utils.determineWinner
import com.example.kotlinjokenpo.utils.generateRandomChoice
import com.example.kotlinjokenpo.utils.generateRoomNumber
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.kotlinjokenpo.data.PlayerStatus
import com.example.kotlinjokenpo.data.PlayersStatus
import com.example.kotlinjokenpo.services.firebase.playersStatusListener
import com.example.kotlinjokenpo.services.firebase.updatePlayerStatus

@Composable
fun MultiPlayerScreen(navController: NavController, modifier: Modifier) {
    var roomSelected by remember { mutableStateOf(false) }
    var roomNumber by remember { mutableIntStateOf(0) }
    var ready by remember { mutableStateOf(false) }
    var gameFinished by remember { mutableStateOf(false) }
    var countdown by remember { mutableIntStateOf(6) }

    var player by remember { mutableIntStateOf(1) }

    var player1Choice by remember { mutableIntStateOf(-1) } // -1 indicates no choice made
    var player2Choice by remember { mutableIntStateOf((-1)) }

    var resultMessage by remember { mutableStateOf("") }
    var countdownFinished by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }

    // PLAYER STATUS LISTENER ===========================================
    val playersStatus = remember { mutableStateOf(
        PlayersStatus(
        player1 = PlayerStatus(online = false, ready = false, choice = -1),
        player2 = PlayerStatus(online = false, ready = false, choice = -1)
        )
    ) }
    //Text to Display
    val playerOnlineText = when (player) {
        1 -> if (playersStatus.value.player1.online) "Player 1 Online" else "Player 1 Offline"
        2 -> if (playersStatus.value.player2.online) "Player 2 Online" else "Player 2 Offline"
        else -> "No Player Online"
    }
    // ===============================================================
    val playerReadyText = when (player) {
        1 -> if (playersStatus.value.player1.ready) "Player 1 Ready" else "Player 1 Not Ready"
        2 -> if (playersStatus.value.player2.ready) "Player 2 Ready" else "Player 2 Not Ready"
        else -> "Both Players are not Ready"
    }
    // ===============================================================
    if (roomNumber > 0) {
        val statusFlow = playersStatusListener(roomNumber.toString())
        val status by statusFlow.collectAsState()

        // Update the local state with the fetched status
        LaunchedEffect(status) {
            playersStatus.value = status
        }
    }
    // ===============================================================

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
        resultMessage = determineWinner(playersStatus.value.player1.choice, playersStatus.value.player2.choice)
        Log.d("PlayScreen", "Countdown finished. Player Choice: ${playersStatus.value.player1.choice}, Player 2 Choice: ${playersStatus.value.player2.choice}, Result: $resultMessage")
    }
    // CREATE OR SELECT ROOM =======================================================================
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    if (!roomSelected) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Choose Your Room",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.multiplayer),
                    contentDescription = "Header",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = screenHeight / 2)
                        .padding(bottom = 30.dp),
                    contentScale = ContentScale.Crop
                )
                //Modal
                RoomNumberDialog(
                    openDialog = openDialog,
                    onDismissRequest = { openDialog = false },
                    icon = Icons.Default.Info,
                    onConfirmation = { roomTypedNumber ->
                        openDialog = false
                        // Handle the room number here
                        searchRoom(roomTypedNumber) { success ->
                            if (success) {
                                Log.d("SearchRoom", "Room found and player2 is now online.")
                                roomNumber = roomTypedNumber.toInt()
                                roomSelected = true
                            } else {
                                Log.d("SearchRoom", "Room not found or update failed.")
                            }
                        }
                    }
                )

                IconAndLabelButton(
                    buttonLabel = "Create Room",
                    iconName = Icons.Default.AddCircle,
                    buttonColor = Color(0xFF7F44D4),
                    onClick = {
                        Log.d("button", "Button Create Room Clicked")
                        roomNumber = generateRoomNumber()
                        addNewRoom(roomNumber.toString()) { success ->
                            if (success) {
                                Log.d("CreateRoom","Room $roomNumber added successfully.")
                                roomSelected = true
                                player = 1
                            } else {
                                Log.d("CreateRoom","Failed to add room $roomNumber.")
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                IconAndLabelButton(
                    buttonLabel = "Select a Room",
                    iconName = Icons.Default.Search,
                    buttonColor = Color(0xFF7F44D4),
                    onClick = {
                        Log.d("button", "Button Select a Room Clicked")
                        openDialog = true
                        player = 2
                    }
                )
            }
        }
    } else {
        // UI AREA======================================================================================
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
                if (roomSelected) {
                Text(
                    text = "Room Number $roomNumber",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    ),
                    textAlign = TextAlign.Center
                )}
                Spacer(modifier = Modifier.height(16.dp))

                if (!ready) {
                    Image(
                        painter = painterResource(id = R.drawable.jokenpo),
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

                if (!ready){
                Text(
                    text ="$playerOnlineText\n$playerReadyText",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    textAlign = TextAlign.Center
                )}

                if (ready) {
                    fun getChoiceText(choice: Int): String {
                        return when (choice) {
                            0 -> "You chose Rock"
                            1 -> "You chose Paper"
                            2 -> "You chose Scissors"
                            else -> "Make a choice"
                        }
                    }

                    val choiceText = if (player == 1) {
                        getChoiceText(player1Choice)
                    } else {
                        getChoiceText(player2Choice)
                    }
                    Text(
                        text = choiceText,
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
                                    if (player == 1) {
                                        player1Choice = 0
                                    } else {
                                        player2Choice = 0
                                    }
                                    updatePlayerStatus(player = player, roomNumber = roomNumber.toString(), choice = 0)
                                }
                                Log.d(
                                    "PlayScreen",
                                    "BUTTON PRESSED: player1Choice = $player1Choice"
                                )
                            }
                        )
                        IconAndLabelButton(
                            buttonLabel = "Paper",
                            iconName = Icons.Default.AccountCircle,
                            buttonColor = Color(0xFFF7D119),
                            onClick = {
                                if (countdown > 0) {
                                    if (player == 1) {
                                        player1Choice = 1
                                    } else {
                                        player2Choice = 1
                                    }
                                    updatePlayerStatus(player = player, roomNumber = roomNumber.toString(), choice = 1)
                                }
                                Log.d(
                                    "PlayScreen",
                                    "BUTTON PRESSED: player1Choice = $player1Choice"
                                )
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
                                    if (player == 1) {
                                        player1Choice = 2
                                    } else {
                                        player2Choice = 2
                                    }
                                    updatePlayerStatus(player = player, roomNumber = roomNumber.toString(), choice = 2)
                                }
                                Log.d(
                                    "PlayScreen",
                                    "BUTTON PRESSED: player1Choice = $player1Choice"
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (!ready || gameFinished) {
                    // add Text here
                    IconAndLabelButton(
                        buttonLabel = if (gameFinished) "PLAY AGAIN" else "READY",
                        iconName = Icons.Default.PlayArrow,
                        buttonColor = Color(0xFF4CAF50),
                        onClick = {
                            if (gameFinished) {
                                gameFinished = false
                                ready = false
                                countdown = 6
                                player1Choice = -1
                                player2Choice = -1
                                resultMessage = ""
                                countdownFinished = false
                                // Reset game state or navigate to a new game
                            } else {
                                //set ready in 'player'
                                updatePlayerStatus(player = player, roomNumber = roomNumber.toString(), isReady = true)
                                //check if the both players are ready
                                if (playersStatus.value.player1.ready  && playersStatus.value.player2.ready){
                                    ready = true
                                }
                            }
                        })
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
}

@Preview(showBackground = true)
@Composable
fun PreviewMultiPlayerScreen() {
    val navController = rememberNavController()

    KotlinJOKENPOTheme {
        val multiplayer = false
        MultiPlayerScreen(navController = navController, modifier = Modifier)
    }
}

@Composable
fun RoomNumberDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
    icon: ImageVector,
    ) {
    var roomNumberToSearch by remember { mutableStateOf(TextFieldValue()) }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Info Icon",
                    modifier = Modifier.size(44.dp)
                )
            },
            title = { Text("Enter Room Number") },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                    text = "Please enter the room number below:",
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = roomNumberToSearch,
                        onValueChange = { newValue -> roomNumberToSearch = newValue },
                        label = { Text("Room Number") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmation(roomNumberToSearch.text)
                        roomNumberToSearch = TextFieldValue()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { onDismissRequest() }) {
                    Text("Cancel")
                }
            }
        )
    }
}