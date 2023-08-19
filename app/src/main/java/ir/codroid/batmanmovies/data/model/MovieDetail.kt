package ir.codroid.batmanmovies.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.codroid.batmanmovies.util.Constants.TABLE_MOVIE_DETAIL


@Entity(tableName = TABLE_MOVIE_DETAIL)
data class MovieDetail(
    val actors: String,
    val awards: String,
    val boxOffice: String,
    val country: String,
    val DVD: String,
    val director: String,
    val genre: String,
    val language: String,
    val metaScore: String,
    val plot: String,
    val poster: String,
    val production: String,
    val rated: String,
    val ratings: List<Rating>,
    val released: String,
    val response: String,
    val runtime: String,
    val title: String,
    val type: String,
    val website: String,
    val writer: String,
    val year: String,
    @PrimaryKey(autoGenerate = false)
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
) {
    data class Rating(
        val Source: String,
        val Value: String
    )
}