package com.devdatt.pratiti.data.mapper

import com.devdatt.pratiti.data.model.CategoryDto
import com.devdatt.pratiti.data.model.TrackDto
import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.model.Track

/** Converts catalog JSON DTOs into domain models used by UI / use cases. */
fun CategoryDto.toDomain(): Category = Category(
    id = id,
    name = name,
    displayName = displayName
)

fun TrackDto.toDomain(): Track = Track(
    id = id,
    title = title,
    categoryId = categoryId,
    fileName = fileName
)
