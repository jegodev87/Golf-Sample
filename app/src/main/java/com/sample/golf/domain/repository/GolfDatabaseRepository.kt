package com.sample.golf.domain.repository

import com.sample.golf.data.model.dto.CourseDTO
import com.sample.golf.data.model.entity.CourseEntity
import com.sample.golf.domain.model.GolfCourse

interface GolfDatabaseRepository {

    suspend fun getCachedGolfCourses(query: String): List<CourseEntity>?

    suspend fun saveGolfCourses(golfCourses: List<CourseEntity>, query: String)
}