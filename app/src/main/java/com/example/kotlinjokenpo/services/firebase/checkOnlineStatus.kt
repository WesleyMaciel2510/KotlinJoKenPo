package com.example.kotlinjokenpo.services.firebase

import android.util.Log
import androidx.compose.runtime.*
import com.example.kotlinjokenpo.data.PlayerStatus
import com.example.kotlinjokenpo.data.PlayersStatus
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun PlayersStatusListener(roomNumber: String): StateFlow<PlayersStatus> {
    val database = FirebaseDatabase.getInstance()
    val player1OnlineRef = database.getReference("JokenPo/rooms/$roomNumber/players/player1/online")
    val player1ReadyRef = database.getReference("JokenPo/rooms/$roomNumber/players/player1/ready")
    val player2OnlineRef = database.getReference("JokenPo/rooms/$roomNumber/players/player2/online")
    val player2ReadyRef = database.getReference("JokenPo/rooms/$roomNumber/players/player2/ready")

    val playersStatus = remember { MutableStateFlow(
        PlayersStatus(
            player1 = PlayerStatus(online = false, ready = false),
            player2 = PlayerStatus(online = false, ready = false)
        )
    ) }

    DisposableEffect(roomNumber) {
        val player1OnlineListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val online = snapshot.getValue(Boolean::class.java) ?: false
                val currentStatus = playersStatus.value.copy(player1 = playersStatus.value.player1.copy(online = online))
                playersStatus.value = currentStatus
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Failed to read player1 online status.", error.toException())
            }
        }
        val player1ReadyListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val ready = snapshot.getValue(Boolean::class.java) ?: false
                val currentStatus = playersStatus.value.copy(player1 = playersStatus.value.player1.copy(ready = ready))
                playersStatus.value = currentStatus
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Failed to read player1 ready status.", error.toException())
            }
        }
        val player2OnlineListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val online = snapshot.getValue(Boolean::class.java) ?: false
                val currentStatus = playersStatus.value.copy(player2 = playersStatus.value.player2.copy(online = online))
                playersStatus.value = currentStatus
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Failed to read player2 online status.", error.toException())
            }
        }
        val player2ReadyListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val ready = snapshot.getValue(Boolean::class.java) ?: false
                val currentStatus = playersStatus.value.copy(player2 = playersStatus.value.player2.copy(ready = ready))
                playersStatus.value = currentStatus
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Failed to read player2 ready status.", error.toException())
            }
        }

        player1OnlineRef.addValueEventListener(player1OnlineListener)
        player1ReadyRef.addValueEventListener(player1ReadyListener)
        player2OnlineRef.addValueEventListener(player2OnlineListener)
        player2ReadyRef.addValueEventListener(player2ReadyListener)

        onDispose {
            player1OnlineRef.removeEventListener(player1OnlineListener)
            player1ReadyRef.removeEventListener(player1ReadyListener)
            player2OnlineRef.removeEventListener(player2OnlineListener)
            player2ReadyRef.removeEventListener(player2ReadyListener)
        }
    }

    return playersStatus
}


