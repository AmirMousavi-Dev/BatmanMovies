package ir.codroid.batmanmovies.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import ir.codroid.batmanmovies.ui.SharedViewModel
import ir.codroid.batmanmovies.ui.screen.MovieDetailScreen
import ir.codroid.batmanmovies.ui.screen.MovieListScreen
import ir.codroid.batmanmovies.ui.screen.SplashScreen
import ir.codroid.batmanmovies.util.Constants.SPLASH_SCREEN


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavigation(
    viewModel: SharedViewModel = hiltViewModel(),
    navController: NavHostController
) {

    AnimatedNavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(route = Screen.MovieList.route) {
            MovieListScreen(navController)
        }

        composable(route = Screen.MovieDetail.route) {
            MovieDetailScreen(navController)
        }
    }
}
