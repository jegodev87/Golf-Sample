package com.sample.golf.domain.model

data class GolfCourse(
    val id: Int,
    val clubName: String,
    val courseName: String,
    val location: Location
)

data class Location(
    val address: String? = ""
)
