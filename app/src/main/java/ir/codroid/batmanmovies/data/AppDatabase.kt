package ir.codroid.batmanmovies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.codroid.batmanmovies.data.dao.MovieDao
import ir.codroid.batmanmovies.data.dao.MovieDetailDao
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.model.MovieDetail

@Database(
    entities = [MovieDetail::class, Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RatingTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
}