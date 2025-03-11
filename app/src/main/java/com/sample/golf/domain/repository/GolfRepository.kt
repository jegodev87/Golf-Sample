package com.sample.golf.domain.repository

import com.sample.golf.data.remote.NetworkResult

interface GolfRepository {
    suspend fun searchGolfLocations() : NetworkResult<List<Any>>
}