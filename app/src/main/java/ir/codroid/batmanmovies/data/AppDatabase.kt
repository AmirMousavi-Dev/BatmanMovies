package ir.codroid.batmanmovies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.codroid.batmanmovies.data.dao.MovieDao
import ir.codroid.batmanmovies.data.dao.MovieDetailDao
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.model.Movies

@Database(
    entities = [MovieDetail::class, Movies.Movie::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
}