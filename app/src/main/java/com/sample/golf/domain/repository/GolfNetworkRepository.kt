package com.sample.golf.domain.repository

import com.sample.golf.data.model.dto.CourseDTO
import com.sample.golf.data.model.dto.GolfCourseResponse
import com.sample.golf.data.source.remote.NetworkResult

interface GolfNetworkRepository {
    suspend fun searchGolfLocations(searchQuery : String) : NetworkResult<GolfCourseResponse>
}