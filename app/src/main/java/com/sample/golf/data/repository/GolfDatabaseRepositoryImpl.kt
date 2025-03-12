package com.sample.golf.data.repository

import com.sample.golf.data.model.entity.CourseEntity
import com.sample.golf.data.source.local.room.dao.GolfDao
import com.sample.golf.domain.repository.GolfDatabaseRepository
import javax.inject.Inject

class GolfDatabaseRepositoryImpl @Inject constructor(
    private val golfDao: GolfDao
) : GolfDatabaseRepository {
    override suspend fun getCachedGolfCourses(query: String): List<CourseEntity>? {
        return golfDao.getCourseByName(query)
    }

    override suspend fun saveGolfCourses(golfCourses: List<CourseEntity>, query: String) {
       return golfDao.insertGolfCourses(golfCourses)
    }


}
