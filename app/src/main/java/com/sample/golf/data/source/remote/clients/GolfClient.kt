package com.sample.golf.data.source.remote.clients

import com.sample.golf.data.model.dto.CourseDTO
import com.sample.golf.data.model.dto.GolfCourseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GolfClient {

    @GET("v1/search")
    suspend fun searchGolfCourse(@Query("search_query") query: String): Response<GolfCourseResponse>

}