package com.example.kotlinjokenpo.utils

import kotlin.random.Random

fun generateRandomChoice(): Int {
    return Random.nextInt(0, 3)
    // Generates a random number between 0 and 2
}