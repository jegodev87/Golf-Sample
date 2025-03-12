package com.sample.golf.data.repository

import com.sample.golf.data.model.dto.CourseDTO
import com.sample.golf.data.model.dto.GolfCourseResponse
import com.sample.golf.data.source.remote.ApiHandler
import com.sample.golf.data.source.remote.NetworkResult
import com.sample.golf.data.source.remote.clients.GolfClient
import com.sample.golf.domain.repository.GolfNetworkRepository
import javax.inject.Inject

class GolfNetworkRepositoryImpl @Inject constructor(
    private val apiHandler: ApiHandler,
    private val golfClient: GolfClient,
) : GolfNetworkRepository {

    override suspend fun searchGolfLocations(searchQuery: String): NetworkResult<GolfCourseResponse> {
        return apiHandler.executeApiCall { golfClient.searchGolfCourse(searchQuery) }
    }


}
