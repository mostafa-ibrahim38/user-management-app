package com.trendsoftware.usermanagementapp.data.mapper

import com.trendsoftware.usermanagementapp.data.local.entity.UserEntity
import com.trendsoftware.usermanagementapp.domain.entity.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        age = age,
        jobTitle = jobTitle,
        gender = gender
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        name = name,
        age = age,
        jobTitle = jobTitle,
        gender = gender
    )
}