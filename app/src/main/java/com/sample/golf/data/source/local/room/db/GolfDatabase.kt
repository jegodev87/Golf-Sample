package com.sample.golf.data.source.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.golf.data.model.entity.CourseEntity
import com.sample.golf.data.source.local.room.dao.GolfDao

@Database(entities = [CourseEntity::class], version = 1, exportSchema = false)
abstract class GolfDatabase : RoomDatabase() {
    abstract fun golfCourseDao(): GolfDao
}