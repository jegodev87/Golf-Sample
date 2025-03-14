package com.sample.golf.data.source.remote

import retrofit2.Response

interface ApiHandler {
    suspend fun <T : Any> executeApiCall(execute: suspend () -> Response<T>): NetworkResult<T>
}
