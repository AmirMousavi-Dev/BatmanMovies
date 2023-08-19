package ir.codroid.batmanmovies.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import ir.codroid.batmanmovies.data.dao.MovieDao
import ir.codroid.batmanmovies.data.dao.MovieDetailDao
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.model.Movies
import ir.codroid.batmanmovies.data.remote.ApiService
import ir.codroid.batmanmovies.data.remote.BaseApiResponse
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import javax.inject.Inject

@ViewModelScoped
class MovieRepository
@Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val movieDetailDao: MovieDetailDao
) : BaseApiResponse() {

    suspend fun getMovieListRemote(): NetWorkResult<Movies> =
        safeApiCall {
            apiService.getMovieList()
        }

    suspend fun getMovieDetailRemote(imdbID: Int): NetWorkResult<MovieDetail> =
        safeApiCall {
            apiService.getMovieDetail(imdbID)
        }

    suspend fun getMovieListLocal() = movieDao.getMovieList()
    suspend fun insertMovieListLocal(movies: List<Movies.Movie>) = movieDao.insertMovies(movies)

    suspend fun getMovieDetailLocal(imdbID: Int) = movieDetailDao.getMovie(imdbID)
    suspend fun insertMovieDetailLocal(movieDetail: MovieDetail) =
        movieDetailDao.insertMovieDetail(movieDetail)

}