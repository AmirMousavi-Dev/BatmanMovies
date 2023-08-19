package ir.codroid.batmanmovies.data.remote

import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.model.Movies
import ir.codroid.batmanmovies.data.model.ResponseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("batman")
    suspend fun getMovieList(): Response<ResponseResult<Movies>>

    @GET("{imdbID}")
    suspend fun getMovieDetail(
        @Path("imdbID") imdbID: Int
    ): Response<ResponseResult<MovieDetail>>
}