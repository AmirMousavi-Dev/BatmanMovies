package ir.codroid.batmanmovies.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import ir.codroid.batmanmovies.ui.screen.detail.MovieDetailScreen
import ir.codroid.batmanmovies.ui.screen.list.MovieListScreen
import ir.codroid.batmanmovies.ui.screen.SplashScreen
import ir.codroid.batmanmovies.ui.screen.about_batman.AboutBatman


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavigation(
    navController: NavHostController
) {

    AnimatedNavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(route = Screen.Splash.route,enterTransition = {
            slideInVertically (
                initialOffsetY = { fullWidth -> fullWidth },
                animationSpec = tween(
                    durationMillis = 700
                )
            )
        },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = 700
                    )
                )
            },) {
            SplashScreen(navController)
        }

        composable(route = Screen.MovieList.route
        ,enterTransition = {
                slideInVertically (
                    initialOffsetY = { fullWidth -> fullWidth },
                    animationSpec = tween(
                        durationMillis = 700
                    )
                )
            },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = 700
                    )
                )
            },) {
            MovieListScreen(navController)
        }
        composable(route = Screen.AboutBatman  .route ,enterTransition = {
            slideInVertically (
                initialOffsetY = { fullWidth -> fullWidth },
                animationSpec = tween(
                    durationMillis = 700
                )
            )
        },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = 700
                    )
                )
            },) {
            AboutBatman()
        }

        composable(route = Screen.MovieDetail.route + "/{imdbID}" ,
        enterTransition = {
            slideInVertically (
                initialOffsetY  = { fullWidth -> fullWidth },
                animationSpec = tween(
                    durationMillis = 700
                )
            )
        },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = 700
                    )
                )
            },
        arguments = listOf(navArgument("imdbID"){
            type = NavType.StringType
        })
        ) { navBackStackEntry ->
            val imdbID = navBackStackEntry.arguments?.getString("imdbID")
            imdbID?.let {
            MovieDetailScreen(navController , imdbID = it)
            }
        }
    }
}
