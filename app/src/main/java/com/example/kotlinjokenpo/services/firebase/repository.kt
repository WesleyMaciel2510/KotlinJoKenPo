/*
package com.example.kotlinjokenpo.services.firebase

import com.example.kotlinjokenpo.data.JokenPoGame
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class FirestoreRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun createGame(game: JokenPoGame, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection("JokenPo").document(game.gameId)
            .set(game)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun getGame(gameId: String, onComplete: (JokenPoGame?) -> Unit) {
        firestore.collection("JokenPo").document(gameId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val game = document.toObject(JokenPoGame::class.java)
                    onComplete(game)
                } else {
                    onComplete(null)
                }
            }
            .addOnFailureListener {
                onComplete(null)
            }
    }

    fun updateGame(game: JokenPoGame, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection("JokenPo").document(game.gameId)
            .set(game)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun addGameListener(gameId: String, onUpdate: (JokenPoGame?) -> Unit): ListenerRegistration {
        return firestore.collection("JokenPo").document(gameId)
            .addSnapshotListener { snapshot, e ->
                if (e != null || snapshot == null || !snapshot.exists()) {
                    onUpdate(null)
                    return@addSnapshotListener
                }
                val game = snapshot.toObject(JokenPoGame::class.java)
                onUpdate(game)
            }
    }
}
*/
