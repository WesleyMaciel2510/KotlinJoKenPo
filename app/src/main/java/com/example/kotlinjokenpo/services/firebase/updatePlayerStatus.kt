package com.example.kotlinjokenpo.services.firebase

import android.util.Log
import com.google.firebase.database.FirebaseDatabase

fun updatePlayerStatus(player: Int, roomNumber: String, isReady: Boolean? = null, choice: String? = null) {
    val database = FirebaseDatabase.getInstance()

    // Construct the path based on the player number
    val playerPath = when (player) {
        1 -> "JokenPo/rooms/$roomNumber/players/player1/"
        2 -> "JokenPo/rooms/$roomNumber/players/player2/"
        else -> {
            Log.e("FirebaseUpdate", "Invalid player number: $player")
            return
        }
    }

    // Get a reference to the Firebase path
    val playerRef = database.getReference(playerPath)

    // Create a map to update the values
    val updates = mutableMapOf<String, Any>()

    // Update "ready" if provided
    isReady?.let {
        updates["ready"] = it
    }

    // Update "choice" if provided
    choice?.let {
        updates["choice"] = it
    }

    // Perform the update if there's anything to update
    if (updates.isNotEmpty()) {
        playerRef.updateChildren(updates)
            .addOnSuccessListener {
                Log.d("FirebaseUpdate", "Player $player status updated successfully.")
            }
            .addOnFailureListener { error ->
                Log.e("FirebaseUpdate", "Failed to update player $player status.", error)
            }
    } else {
        Log.e("FirebaseUpdate", "No update parameters provided.")
    }
}
