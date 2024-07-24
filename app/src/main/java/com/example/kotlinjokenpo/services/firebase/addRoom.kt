package com.example.kotlinjokenpo.services.firebase

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

fun addNewRoom(roomNumber: String,  callback: (Boolean) -> Unit) {
    Log.d("CreateRoom","Called addNewRoom")

    // Get a reference to the Firebase database
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val roomsRef: DatabaseReference = database.getReference("JokenPo/rooms")
    Log.d("CreateRoom","Got roomsRef Successfully = $roomsRef")

    // Create a new room structure
    val newRoom = mapOf(
        "players" to mapOf(
            "player1" to mapOf(
                "name" to "Player 1",
                "choice" to -1,
                "ready" to false,
                "online" to true,
            ),
            "player2" to mapOf(
                "name" to "Player 2",
                "choice" to -1,
                "ready" to false,
                "online" to false,
            )
        ),
        "game_status" to mapOf(
            "state" to "waiting_for_players",
            "timeout_duration" to 5
        )
    )

    // Set the new room in the database and handle the result
    val task: Task<Void> = roomsRef.child(roomNumber).setValue(newRoom)
    task.addOnSuccessListener {
        // Notify success
        Log.d("CreateRoom","Success to add Room")
        callback(true)
    }
    task.addOnFailureListener { exception ->
        // Notify failure
        Log.d("CreateRoom","Failed to add room: ${exception.message}")
        callback(false)
    }
}