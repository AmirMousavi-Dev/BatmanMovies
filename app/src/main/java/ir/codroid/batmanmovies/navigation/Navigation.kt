package ir.codroid.batmanmovies.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import ir.codroid.batmanmovies.ui.screen.detail.MovieDetailScreen
import ir.codroid.batmanmovies.ui.screen.list.MovieListScreen
import ir.codroid.batmanmovies.ui.screen.SplashScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavigation(
    navController: NavHostController
) {

    AnimatedNavHost(navController = navController, startDestination = Screen.MovieList.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(route = Screen.MovieList.route) {
            MovieListScreen(navController)
        }
        composable(route = Screen.Favorite  .route) {
            MovieListScreen(navController)
        }

        composable(route = Screen.MovieDetail.route + "/{imdbID}" ,
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
