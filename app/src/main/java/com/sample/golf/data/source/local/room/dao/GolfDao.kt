package com.sample.golf.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.golf.data.model.entity.CourseEntity

@Dao
interface GolfDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGolfCourses(course: List<CourseEntity>)

    @Query(
        """
        SELECT * FROM `golf-course-entity` 
        WHERE club_name LIKE :query 
        OR course_name LIKE :query
    """
    )
    suspend fun getCourseByName(query: String): List<CourseEntity>?

}
