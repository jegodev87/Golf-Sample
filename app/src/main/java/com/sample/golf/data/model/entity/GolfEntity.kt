package com.sample.golf.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "golf-course-entity")
data class CourseEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "club_name")
    val clubName: String,

    @ColumnInfo(name = "course_name")
    val courseName: String,

    @Embedded
    val location: LocationEntity
)

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "location_id")
    val id: Int = 0,

    @ColumnInfo(name = "address")
    val address: String? = ""
)
