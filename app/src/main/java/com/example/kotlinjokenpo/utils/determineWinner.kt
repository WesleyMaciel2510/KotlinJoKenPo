package com.example.kotlinjokenpo.utils

fun determineWinner(playerChoice: Int, player2Choice: Int): String {
    // Map integers to their corresponding choice names
    val choiceLabels = mapOf(
        0 to "Paper",
        1 to "Rock",
        2 to "Scissors"
    )

    // Retrieve choice labels based on player choices
    val player1ChoiceLabel = choiceLabels[playerChoice] ?: "Unknown"
    val player2ChoiceLabel = choiceLabels[player2Choice] ?: "Unknown"

    var errorMessage = ""

    // Check for invalid choices and set an appropriate error message
    if (playerChoice !in 0..2) {
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
        playerChoice == player2Choice -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nIt's a tie!"
        playerChoice == 0 && player2Choice == 1 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nPaper covers Rock.\nPlayer 2 wins!"
        playerChoice == 0 && player2Choice == 2 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nRock crushes Scissors.\nPlayer 1 wins!"
        playerChoice == 1 && player2Choice == 0 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nPaper covers Rock.\nPlayer 1 wins!"
        playerChoice == 1 && player2Choice == 2 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nScissors cuts Paper.\nPlayer 2 wins!"
        playerChoice == 2 && player2Choice == 0 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nRock crushes Scissors.\nPlayer 2 wins!"
        playerChoice == 2 && player2Choice == 1 -> "Player 1 Choice: $player1ChoiceLabel,\nPlayer 2 Choice: $player2ChoiceLabel\nScissors cuts Paper.\nPlayer 1 wins!"
        else -> errorMessage
    }
}
