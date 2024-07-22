package com.example.kotlinjokenpo.utils

import kotlin.random.Random

fun generateRandomRangeWithInterval(min: Int, max: Int, minInterval: Int, maxInterval: Int): Pair<Int, Int> {
    require(min < max) { "Min must be less than Max" }
    require(maxInterval > 0) { "Interval must be greater than 0" }
    require(maxInterval > minInterval) { "Maximum interval must be greater than minimum interval" }

    val random = Random.Default
    var start: Int
    var end: Int

    do {
        start = random.nextInt(min, max + 1)
        end = random.nextInt(start, (start + maxInterval).coerceAtMost(max) + 1)
    } while (end <= start)

    return Pair(start, end)
}