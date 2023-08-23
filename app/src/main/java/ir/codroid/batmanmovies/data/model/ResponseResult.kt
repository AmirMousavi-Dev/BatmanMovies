package ir.codroid.batmanmovies.data.model

data class ResponseResult<T>(
    val Search: T,
    val response: Boolean,
    val totalResults: Int,
)
