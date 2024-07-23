package com.example.kotlinjokenpo.screens.multiPlayer

import android.util.Log
import com.example.kotlinjokenpo.data.JokenPoGame
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

// Define the function to setup the Firebase listener
fun setupGameListener(gameRef: DatabaseReference, gameId: String, onGameUpdated: (JokenPoGame) -> Unit) {
    val gameListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val game = dataSnapshot.getValue(JokenPoGame::class.java) ?: JokenPoGame()
            onGameUpdated(game)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("GameLogic", "Failed to read value.", databaseError.toException())
        }
    }
    gameRef.child(gameId).addValueEventListener(gameListener)
}

fun handleReadyClick(
    gameRef: DatabaseReference,
    gameId: String,
    playerId: String,
    playerChoice: Int,
    isReady: Boolean
) {
    // Update the player's readiness status in Firebase
    gameRef.child(gameId).child("players").child(playerId).updateChildren(mapOf(
        "ready" to isReady,
        "choice" to if (isReady) playerChoice else null
    ))

    // Check the readiness status of both players
    gameRef.child(gameId).child("players").get().addOnSuccessListener { snapshot ->
        val player1Ready = snapshot.child("player1").child("ready").getValue(Boolean::class.java) ?: false
        val player2Ready = snapshot.child("player2").child("ready").getValue(Boolean::class.java) ?: false

        if (!player1Ready) {
            Log.d("GameLogic", "Player 1 is not ready yet")
        } else if (!player2Ready) {
            Log.d("GameLogic", "Player 2 is not ready yet")
        } else {
            Log.d("GameLogic", "Both players are ready")
            // Handle game start logic here
            startGame(gameRef, gameId)
        }
    }.addOnFailureListener {
        Log.w("GameLogic", "Failed to check players' readiness", it)
    }
}

fun startGame(gameRef: DatabaseReference, gameId: String) {
    // Update game status to indicate that the game has started
    gameRef.child(gameId).updateChildren(mapOf("status" to "started"))
}

// Define the function to handle choice button clicks
fun handleChoiceClick(gameRef: DatabaseReference, gameId: String, playerChoice: Int) {
    gameRef.child(gameId).child("players").child("player1").child("choice").setValue(playerChoice)
}


