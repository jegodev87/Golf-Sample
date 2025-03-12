package com.sample.golf.domain.usecases

import com.sample.golf.data.mapper.toDomain
import com.sample.golf.data.mapper.toEntity
import com.sample.golf.data.source.remote.NetworkResult
import com.sample.golf.domain.model.GolfCourse
import com.sample.golf.domain.repository.GolfDatabaseRepository
import com.sample.golf.domain.repository.GolfNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GolfDetailsUseCase @Inject constructor(
    private val golfNetworkRepository: GolfNetworkRepository,
    private val golfDatabaseRepository: GolfDatabaseRepository
) {

     suspend fun searchGolfLocations(searchQuery: String): Flow<NetworkResult<List<GolfCourse>>> {
         return flow {
             // Emit loading state first (Optional but recommended for UI feedback)
             emit(NetworkResult.Loading)

             // Try fetching from the database first
            /* val cachedGolfCourses = golfDatabaseRepository.getCachedGolfCourses(searchQuery)

             // If data exists in the DB, emit it as the initial value
             if (!cachedGolfCourses.isNullOrEmpty()) {
                 emit(NetworkResult.Success(200, cachedGolfCourses.map { it.toDomain() }))
             }
*/
             // Fetch from the network if no cached data is found
             when (val result = golfNetworkRepository.searchGolfLocations(searchQuery)) {
                 is NetworkResult.Success -> {
                     val golfCourses = result.data.courses
                     val golfEntity = golfCourses.map { it.toEntity() }

                     // Save the fetched courses to the database
                     golfDatabaseRepository.saveGolfCourses(golfEntity, searchQuery)

                     // Emit the data from the network as success
                     emit(NetworkResult.Success(200, golfCourses.map { it.toDomain() }))
                 }
                 is NetworkResult.Error -> {
                     // Emit error state if network fetch fails
                     emit(NetworkResult.Error(result.code, result.errorMsg))
                 }
                 is NetworkResult.Exception -> {
                     // Emit exception state if an exception occurs
                     emit(NetworkResult.Exception(result.e))
                 }

                 NetworkResult.Loading -> emit(NetworkResult.Loading)
             }
         }.flowOn(Dispatchers.IO)
    }
}