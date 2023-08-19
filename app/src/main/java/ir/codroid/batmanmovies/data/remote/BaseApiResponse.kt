package ir.codroid.batmanmovies.data.remote

import ir.codroid.batmanmovies.data.model.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<ResponseResult<T>>): NetWorkResult<T> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        return@withContext NetWorkResult.Success(body.data, body.totalResults)
                    }
                }
                return@withContext error("code : ${response.code()} message : data is null")
            } catch (e: Exception) {
                return@withContext error("message : ${e.message}")

            }
        }

    private fun <T> error(errorMessage: String): NetWorkResult<T> =
        NetWorkResult.Error(errorMessage = " Api call failed : $errorMessage")
}