package com.example.kotlinjokenpo.utils

import android.util.Log

fun determineWinner(player1Choice: Int, player2Choice: Int): String {
    Log.d("PlayScreen", "determineWinner called! $player1Choice, $player2Choice")
    // Map integers to their corresponding choice names
    val choiceLabels = mapOf(
        0 to "Rock",
        1 to "Paper",
        2 to "Scissors"
    )

    // Retrieve choice labels based on player choices
    val player1ChoiceLabel = choiceLabels[player1Choice] ?: "Unknown"
    val player2ChoiceLabel = choiceLabels[player2Choice] ?: "Unknown"

    var errorMessage = ""

    // Check for invalid choices and set an appropriate error message
    if (player1Choice !in 0..2) {
        errorMessage = "Player 1 did not choose a valid option!"
    }
    if (player2Choice !in 0..2) {
        errorMessage = "Player 2 did not choose a valid option!"
    }

    // Return error message if there is one
    if (errorMessage.isNotEmpty()) {
        return errorMessage
    }

    // Determine the result of the game
    return when {
        player1Choice == player2Choice -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nIt's a tie!"
        player1Choice == 0 && player2Choice == 1 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nPaper covers Rock.\nPlayer 2 wins!"
        player1Choice == 0 && player2Choice == 2 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nRock crushes Scissors.\nPlayer 1 wins!"
        player1Choice == 1 && player2Choice == 0 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nPaper covers Rock.\nPlayer 1 wins!"
        player1Choice == 1 && player2Choice == 2 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nScissors cuts Paper.\nPlayer 2 wins!"
        player1Choice == 2 && player2Choice == 0 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nRock crushes Scissors.\nPlayer 2 wins!"
        player1Choice == 2 && player2Choice == 1 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nScissors cuts Paper.\nPlayer 1 wins!"
        else -> errorMessage
    }
}
