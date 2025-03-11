package com.sample.golf.data.remote

data class ErrorResponse(
    val status: Int,
    val message: String,
    val error: String
)