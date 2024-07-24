package com.example.kotlinjokenpo.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class PlayersStatus(
    val player1: PlayerStatus,
    val player2: PlayerStatus
)

data class PlayerStatus(
    val online: Boolean,
    val ready: Boolean
)