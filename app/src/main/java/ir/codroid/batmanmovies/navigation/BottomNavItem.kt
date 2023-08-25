package ir.codroid.batmanmovies.navigation

import androidx.annotation.DrawableRes
import ir.codroid.batmanmovies.R

enum class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int
) {
    MovieList(Screen.MovieList.route, R.drawable.ic_home),
    Favorite(Screen.AboutBatman.route, R.drawable.ic_batman)
}
