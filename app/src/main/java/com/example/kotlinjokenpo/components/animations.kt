import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kotlinjokenpo.R

@Composable
fun LottieAnimationView(
    modifier: Modifier = Modifier,
    animationName: Int,
    looping: Boolean = true,
    isPlaying: Boolean = true
) {
    // Load the Lottie animation JSON file
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animationName)
    )

    // Determine the number of iterations
    val iterations = if (looping) LottieConstants.IterateForever else 1

    // Animate the Lottie composition
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iterations,
        isPlaying = isPlaying,
        restartOnPlay = true
    )

    // Display the animation
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewLottieAnimationView() {
    LottieAnimationView(
        animationName = R.raw.countdown,
        looping = true,
        isPlaying = true,
        modifier = Modifier.size(100.dp)
    )
}
