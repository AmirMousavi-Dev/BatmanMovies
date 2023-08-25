package ir.codroid.batmanmovies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.util.Constants.TABLE_MOVIE_DETAIL

@Dao
interface MovieDetailDao {

    @Insert(entity = MovieDetail::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieDetail(movieDetail: MovieDetail)

    @Query("SELECT * FROM $TABLE_MOVIE_DETAIL WHERE imdbID LIKE :imdbID")
    suspend fun getMovie(imdbID: String): MovieDetail

    @Query("SELECT EXISTS(SELECT * FROM $TABLE_MOVIE_DETAIL WHERE imdbID = :imdbId)")
    fun isMovieExist(imdbId : String) : Boolean
}