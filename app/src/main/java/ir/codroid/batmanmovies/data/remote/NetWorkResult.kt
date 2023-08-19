package ir.codroid.batmanmovies.data.remote

import ir.codroid.batmanmovies.data.model.Movies

sealed class NetWorkResult<T>(
    val data: T? = null,
    val response: String? = null,
    val totalResults: String? = null
) {
    class Success<T>(data: T, response: String, totalResults: String) :
        NetWorkResult<T>(data, response, totalResults)

    class Error<T>(data: T? = null, response: String, totalResults: String) :
        NetWorkResult<T>(data, response, totalResults)

    class Loading<T>() : NetWorkResult<T>()
}
