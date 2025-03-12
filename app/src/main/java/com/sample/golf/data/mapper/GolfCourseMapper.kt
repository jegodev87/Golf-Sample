package com.sample.golf.data.mapper

import com.sample.golf.data.model.dto.CourseDTO
import com.sample.golf.data.model.dto.LocationDTO
import com.sample.golf.data.model.entity.CourseEntity
import com.sample.golf.data.model.entity.LocationEntity
import com.sample.golf.domain.model.GolfCourse
import com.sample.golf.domain.model.Location

/**
 * Mapping network dto to entity
 */
fun CourseDTO.toEntity(): CourseEntity {
    return CourseEntity(
        id = this.id,
        clubName = this.clubName,
        courseName = this.courseName,
        location = this.location.toEntity() // Mapping LocationDto to LocationEntity
    )
}
/**
 * Mapping Location dto to entity
 */
fun LocationDTO.toEntity(): LocationEntity {
    return LocationEntity(
        address = this.address
    )
}

/**
 * Mapping entity to domain for showing in the ui
 */
fun CourseEntity.toDomain(): GolfCourse {
    return GolfCourse(
        id = this.id,
        clubName = this.clubName,
        courseName = this.courseName,
        location = this.location.toDomain() // Mapping LocationEntity to Location
    )
}
/**
 * Mapping entity to domain for showing in the ui
 */
fun LocationEntity.toDomain(): Location {
    return Location(
        address = this.address
    )
}

/**
 * Mapping domain to entity
 */
fun GolfCourse.toEntity(): CourseEntity {
    return CourseEntity(
        id = this.id,
        clubName = this.clubName,
        courseName = this.courseName,
        location = this.location.toEntity() // Mapping Location to LocationEntity
    )
}

fun Location.toEntity(): LocationEntity {
    return LocationEntity(
        address = this.address
    )
}


fun CourseDTO.toDomain(): GolfCourse {
    return GolfCourse(
        id = this.id,
        clubName = this.clubName,
        courseName = this.courseName,
        location = this.location.toDomain() // Mapping LocationDto to Location
    )
}

fun LocationDTO.toDomain(): Location {
    return Location(
        address = this.address
    )
}