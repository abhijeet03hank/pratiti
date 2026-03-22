package com.devdatt.pratiti.domain.mapper

import com.devdatt.pratiti.domain.model.Category

fun String.toCategory(): Category {
    return when (this) {
        "Stavan" -> Category.STAVAN
        "Bhajan" -> Category.BHAJAN
        "Aarti" -> Category.AARTI
        else -> Category.STAVAN
    }
}