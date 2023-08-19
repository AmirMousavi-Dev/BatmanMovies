package ir.codroid.batmanmovies.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.codroid.batmanmovies.data.AppDatabase
import ir.codroid.batmanmovies.util.Constants.BATMAN_MOVIES_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BATMAN_MOVIES_DATABASE
        )
            .build()

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()

    @Singleton
    @Provides
    fun provideMovieDetailDao(appDatabase: AppDatabase) = appDatabase.movieDetailDao()
}
