package ir.codroid.batmanmovies.data.remote

import android.util.Log
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
                    Log.e("3121" , "null body")
                    body?.let {
                    Log.e("3121" , "not null body")
                        return@withContext NetWorkResult.Success(data = body.Search)
                    Log.e("3121" , "after returnd")
                    }
                }
                return@withContext error("code : ${response.code()} message : ${response.message()}" , false)
            } catch (e: Exception) {
                return@withContext error("message : ${e.message}" , false)

            }
        }

    private fun <T> error(errorMessage: String , response: Boolean): NetWorkResult<T> =
        NetWorkResult.Error(error = " Api call failed : $errorMessage" , response = response)
}