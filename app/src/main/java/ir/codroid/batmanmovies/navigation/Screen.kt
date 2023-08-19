package ir.codroid.batmanmovies.navigation

import androidx.navigation.NavHostController
import ir.codroid.batmanmovies.util.Constants.MOVIE_DETAIL_SCREEN
import ir.codroid.batmanmovies.util.Constants.MOVIE_LIST_SCREEN
import ir.codroid.batmanmovies.util.Constants.SPLASH_SCREEN

sealed class Screen(val route : String) {
    object Splash : Screen(SPLASH_SCREEN)
    object MovieList : Screen(MOVIE_LIST_SCREEN)
    object MovieDetail : Screen(MOVIE_DETAIL_SCREEN)
}