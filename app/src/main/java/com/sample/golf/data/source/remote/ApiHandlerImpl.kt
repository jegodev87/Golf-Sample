package com.sample.golf.data.source.remote

import com.google.gson.Gson
import com.sample.golf.utils.log
import retrofit2.HttpException
import retrofit2.Response

class ApiHandlerImpl : ApiHandler {
    override suspend fun <T : Any> executeApiCall(execute: suspend () -> Response<T>): NetworkResult<T> {
        return safeApiCall(execute)
    }

    private suspend fun <T : Any> safeApiCall(execute: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                NetworkResult.Success(response.code(), body)
            } else {
                handleApiError(response)
            }

        } catch (e: HttpException) {
            NetworkResult.Error(e.code(), e.message() ?: "HTTP error - something went wrong")
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }


    private fun <T : Any> handleApiError(response: Response<T>): NetworkResult<T> {
        return try {
            val errorResponse = Gson().fromJson(
                response.errorBody()?.string(),
                ErrorResponse::class.java
            )
            NetworkResult.Error(
                code = errorResponse.status,
                errorMsg = errorResponse.message.toString()
            )
        } catch (ex: Exception) {
            log("Error parsing error response: $ex")
            NetworkResult.Error(
                code = response.code(),
                errorMsg = "Unknown error occurred"
            )
        }
    }


}