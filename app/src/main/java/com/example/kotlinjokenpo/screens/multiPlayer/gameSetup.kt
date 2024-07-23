package com.example.kotlinjokenpo.screens.multiPlayer

import android.util.Log
import com.example.kotlinjokenpo.data.GameStatus
import com.example.kotlinjokenpo.data.JokenPoGame
import com.example.kotlinjokenpo.data.Player
import com.example.kotlinjokenpo.data.Round

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

fun setupGame() {
    // Get a reference to the Firebase Realtime Database
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val jokenPoRef: DatabaseReference = database.getReference("JokenPo")

    // Define initial game data
    val initialGameData = JokenPoGame(
        players = mapOf(
            "player1" to Player(
                id = "player1_unique_id",
                name = "Player 1 Name",
                choice = null,
                ready = null
            ),
            "player2" to Player(
                id = "player2_unique_id",
                name = "Player 2 Name",
                choice = null,
                ready = null
            )
        ),
        gameStatus = GameStatus(
            state = "waiting_for_players",
            timeoutDuration = 5
        ),
        rounds = listOf(
            Round(
                roundNumber = 1,
                player1Choice = null,
                player2Choice = null,
                winner = null
            )
        )
    )

    // Write initial data to Firebase
    jokenPoRef.setValue(initialGameData)
        .addOnSuccessListener {
            // Successfully set up the game
            Log.d("GameSetup", "Game setup successfully.")
        }
        .addOnFailureListener { exception ->
            // Failed to set up the game
            Log.e("GameSetup", "Failed to set up the game.", exception)
        }
}




