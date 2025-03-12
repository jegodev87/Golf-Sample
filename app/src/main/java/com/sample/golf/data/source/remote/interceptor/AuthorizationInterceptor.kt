package com.sample.golf.data.source.remote.interceptor

import com.sample.golf.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
) : Interceptor {
    private val apiKey = BuildConfig.GOLF_API_KEY
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Key $apiKey")
            .build()

        return chain.proceed(request)
    }
}