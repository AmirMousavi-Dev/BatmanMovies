package ir.codroid.batmanmovies.util

object Constants {
    const val TABLE_MOVIE = "tb_movie"
    const val TABLE_MOVIE_DETAIL = "tb_movie_detail"
    const val BATMAN_MOVIES_DATABASE = "batman_movies_database"


    const val BASE_URL = "https://www.omdbapi.com/"
    const val TIMEOUT_IN_SECOND = 10L

    const val MOVIE_DETAIL_ARGUMENT_KEY = "imdbId"
    const val MOVIE_LIST_SCREEN = "movie_list"
    const val MOVIE_DETAIL_SCREEN = "movie_detail/{$MOVIE_DETAIL_ARGUMENT_KEY}"
    const val SPLASH_SCREEN = "splash"
    const val SPLASH_SCREEN_DELAY = 3000L
}