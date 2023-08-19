package ir.codroid.batmanmovies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.codroid.batmanmovies.data.model.Movies
import ir.codroid.batmanmovies.util.Constants.TABLE_MOVIE

@Dao
interface MovieDao {

    @Insert(entity = Movies.Movie::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<Movies.Movie>)

    @Query("SELECT * FROM $TABLE_MOVIE")
    suspend fun getMovieList(): List<Movies.Movie>
}