package ir.codroid.batmanmovies.data.remote

import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.model.ResponseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("/")
    suspend fun getMovieList(
        @Query("apikey") apikey :String = "3e974fca" ,
        @Query("s") s :String = "batman" ,
    ): Response<ResponseResult<List<Movie>>>

    @GET("{imdbID}")
    suspend fun getMovieDetail(
        @Path("imdbID") imdbID: Int
    ): Response<ResponseResult<MovieDetail>>
}