package ir.codroid.batmanmovies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.util.Constants.TABLE_MOVIE

@Dao
interface MovieDao {

    @Insert(entity = Movie::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM $TABLE_MOVIE")
    suspend fun getMovieList(): List<Movie>
}