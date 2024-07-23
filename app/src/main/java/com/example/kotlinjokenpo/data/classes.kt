package com.example.kotlinjokenpo.data

data class Player(
    val id: String? = null,
    val name: String? = null,
    val choice: Int? = null,
    val ready: Boolean? = null
)

data class Round(
    val roundNumber: Int? = null,
    val player1Choice: Int? = null,
    val player2Choice: Int? = null,
    val winner: String? = null
)

data class GameStatus(
    val state: String? = null,
    val timeoutDuration: Int? = null
)

data class JokenPoGame(
    val players: Map<String, Player>? = null,
    val gameStatus: GameStatus? = null,
    val rounds: List<Round>? = null
)
