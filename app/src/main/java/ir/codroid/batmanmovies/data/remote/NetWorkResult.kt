package ir.codroid.batmanmovies.data.remote

import ir.codroid.batmanmovies.data.model.Movies

sealed class NetWorkResult<T>(
    val data: T? = null,
    val totalResults: String? = null
) {
    class Success<T>(data: T, totalResults: String) :
        NetWorkResult<T>(data, totalResults)

    class Error<T>(data: T? = null, errorMessage: String) :
        NetWorkResult<T>(data)

    class Loading<T>() : NetWorkResult<T>()
}
