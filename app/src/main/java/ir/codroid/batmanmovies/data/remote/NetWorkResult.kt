package ir.codroid.batmanmovies.data.remote

sealed class NetWorkResult<T>(
    val data: T? = null,
    val response: Boolean? = null,
    val totalResults: Int? = null,
    val error: String? =null
) {
    class Success<T>(data: T) :
        NetWorkResult<T>(data = data,)

    class Error<T>(response : Boolean, error: String) :
        NetWorkResult<T>(response = response , error = error)

    class Loading<T>() : NetWorkResult<T>()
}
