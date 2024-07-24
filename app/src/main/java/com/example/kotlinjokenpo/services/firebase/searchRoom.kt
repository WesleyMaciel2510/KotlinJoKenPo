package com.example.kotlinjokenpo.services.firebase

import com.google.firebase.database.FirebaseDatabase

fun searchRoom(roomNumber: String, callback: (Boolean) -> Unit) {
    val database = FirebaseDatabase.getInstance()
    val roomRef = database.getReference("JokenPo/rooms/$roomNumber")

    roomRef.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val snapshot = task.result
            if (snapshot.exists()) {
                // Room exists, update player2's 'online' status to true
                val updates = mapOf(
                    "players/player2/online" to true
                )
                roomRef.updateChildren(updates).addOnCompleteListener { updateTask ->
                    if (updateTask.isSuccessful) {
                        callback(true)
                    } else {
                        callback(false)
                    }
                }
            } else {
                // Room does not exist
                callback(false)
            }
        } else {
            // Error occurred
            callback(false)
        }
    }
}
