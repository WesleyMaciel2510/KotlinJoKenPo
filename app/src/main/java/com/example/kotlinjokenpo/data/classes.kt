package com.example.kotlinjokenpo.data

data class Player(
    val id: String = "",
    val name: String = "",
    var choice: String? = null,
    val status: String = "waiting",
    val readyTime: Long? = null
)

data class GameStatus(
    var state: String = "waiting_for_players",
    val startTime: Long? = null,
    val endTime: Long? = null,
    val result: String? = null
)

data class Round(
    val roundNumber: Int = 1,
    val player1Choice: String? = null,
    val player2Choice: String? = null,
    val winner: String? = null
)

data class Settings(
    val timeoutDuration: Int = 5,
    val theme: String = "dark",
    val language: String = "en"
)

data class JokenPoGame(
    val gameId: String = "",
    val players: Map<String, Player> = mapOf(
        "player1" to Player(),
        "player2" to Player()
    ),
    val gameStatus: GameStatus = GameStatus(),
    val rounds: List<Round> = listOf(Round()),
    val settings: Settings = Settings()
)
