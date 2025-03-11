package com.sample.golf.data.repository

import com.sample.golf.data.remote.NetworkResult
import com.sample.golf.domain.repository.GolfRepository

class GolfRepositoryImpl : GolfRepository {

    override suspend fun searchGolfLocations(): NetworkResult<List<Any>> {
   return NetworkResult.Success(200, emptyList())
    }

}
