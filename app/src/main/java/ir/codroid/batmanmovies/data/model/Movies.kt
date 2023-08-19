package ir.codroid.batmanmovies.data.model

data class Movies(
    val response: String,
    val data: List<Movie>,
    val totalResults: String
) {
    data class Movie(
        val Poster: String,
        val Title: String,
        val Type: String,
        val Year: String,
        val imdbID: String
    )
}