package com.example.kotlinjokenpo.screens.play

import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()

    fun updateGameField(gameId: String, fieldPath: String, value: Any) {
        // Reference to the game document
        val gameRef = db.collection("JokenPo").document(gameId)

        // Update the specific field
        gameRef.update(fieldPath, value)
            .addOnSuccessListener {
                println("Document successfully updated!")
            }
            .addOnFailureListener { e ->
                println("Error updating document: $e")
            }
    }
}