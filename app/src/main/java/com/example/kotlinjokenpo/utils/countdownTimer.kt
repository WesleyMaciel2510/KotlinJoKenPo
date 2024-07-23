package com.example.kotlinjokenpo.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(
    countdownState: MutableState<Int>,
    onCountdownFinish: () -> Unit,
    onCountdownUpdate: (Int) -> Unit
) {
    Log.d("PlayScreen", "CountdownTimer Called!")

    // Use LaunchedEffect with countdownState.value as a key
    LaunchedEffect(countdownState.value) {
        if (countdownState.value > 0) {
            delay(1000L) // 1 second delay
            countdownState.value -= 1
            onCountdownUpdate(countdownState.value)
            Log.d("PlayScreen", "CountdownTimer being executed!")
        }

        // Ensure that onCountdownFinish is called when countdown reaches zero
        if (countdownState.value <= 0) {
            onCountdownUpdate(0) // Update state to 0 before finishing
            onCountdownFinish()
            Log.d("PlayScreen", "CountdownTimer called onCountdownFinish!")
        }
    }
}

