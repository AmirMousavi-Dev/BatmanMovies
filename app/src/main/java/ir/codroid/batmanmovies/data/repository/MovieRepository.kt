package ir.codroid.batmanmovies.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import ir.codroid.batmanmovies.data.dao.MovieDao
import ir.codroid.batmanmovies.data.dao.MovieDetailDao
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.remote.ApiService
import ir.codroid.batmanmovies.data.remote.BaseApiResponse
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class MovieRepository
@Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val movieDetailDao: MovieDetailDao,
) : BaseApiResponse() {

    suspend fun getMovieListRemote(): NetWorkResult<List<Movie>> =
        safeApiCall {
            apiService.getMovieList()
        }

    suspend fun getMovieDetailRemote(imdbID: String): Response<MovieDetail> =
        apiService.getMovieDetail(i = imdbID)


    suspend fun getMovieListLocal() = movieDao.getMovieList()
    suspend fun insertMovieListLocal(movies: List<Movie>) = movieDao.insertMovies(movies)

    suspend fun getMovieDetailLocal(imdbID: String) = movieDetailDao.getMovie(imdbID)
    suspend fun isMovieExist(imdbID: String) = movieDetailDao.isMovieExist(imdbID)
    suspend fun insertMovieDetailLocal(movieDetail: MovieDetail) =
        movieDetailDao.insertMovieDetail(movieDetail)

}