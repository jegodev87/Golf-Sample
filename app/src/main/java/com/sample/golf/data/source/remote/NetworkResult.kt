package com.sample.golf.data.source.remote

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val code: Int, val data: T) : NetworkResult<T>()
    data class Error<out T : Any>(val code: Int, val errorMsg: String?) : NetworkResult<T>()
    data class Exception(val e: Throwable) : NetworkResult<Nothing>()
    data object  Loading : NetworkResult<Nothing>()
}
