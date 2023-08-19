package ir.codroid.batmanmovies.data.model

data class ResponseResult<T>(
    val data: T,
    val response: String,
    val totalResults: String
)
