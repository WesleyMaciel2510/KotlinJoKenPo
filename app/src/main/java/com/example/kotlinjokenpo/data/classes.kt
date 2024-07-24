package com.example.kotlinjokenpo.data

data class PlayersStatus(
    val player1: PlayerStatus,
    val player2: PlayerStatus
)

data class PlayerStatus(
    val online: Boolean,
    val ready: Boolean,
    val choice: Int,
)