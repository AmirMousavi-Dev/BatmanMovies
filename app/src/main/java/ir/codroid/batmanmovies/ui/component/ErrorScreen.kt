package ir.codroid.batmanmovies.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ir.codroid.batmanmovies.R

@Composable
fun ErrorScreen(isShow : Boolean) {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
             R.raw.error
        )
    )
    if (isShow)
    LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
}