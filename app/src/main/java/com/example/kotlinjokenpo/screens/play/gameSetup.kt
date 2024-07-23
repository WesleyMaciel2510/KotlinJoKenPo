package com.example.kotlinjokenpo.screens.play

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.kotlinjokenpo.data.JokenPoGame
import com.example.kotlinjokenpo.services.firebase.FirestoreRepository
import com.google.firebase.firestore.ListenerRegistration

fun setupGame(gameId: String, onGameUpdated: (JokenPoGame?) -> Unit): ListenerRegistration {
    val firestoreRepository = FirestoreRepository()
    var game by mutableStateOf(JokenPoGame(gameId = gameId))

    // Add a listener to the game data
    val listenerRegistration = firestoreRepository.addGameListener(gameId) { updatedGame ->
        if (updatedGame != null) {
            game = updatedGame
            onGameUpdated(game)
        } else {
            onGameUpdated(null)
        }
    }

    return listenerRegistration
}

