package com.example.kotlinjokenpo.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay


@Composable
fun CountdownTimer(
    countdownState: MutableState<Int>,
    onCountdownUpdate: (Int) -> Unit
) {
    Log.d("PlayScreen", "CountdownTimer Called!")

    LaunchedEffect(countdownState.value) {
        if (countdownState.value > 0) {
            delay(1000L) // 1 second delay
            countdownState.value -= 1
            onCountdownUpdate(countdownState.value)
        }
    }
}

