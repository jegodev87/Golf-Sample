package com.sample.golf.data.model.dto

import com.google.gson.annotations.SerializedName

data class GolfCourseResponse(
    val courses: List<CourseDTO>
)

data class CourseDTO(
    @SerializedName("club_name")
    val clubName: String,
    @SerializedName("course_name")
    val courseName: String,
    val id: Int,
    val location: LocationDTO
)

data class LocationDTO(
    val address: String
)