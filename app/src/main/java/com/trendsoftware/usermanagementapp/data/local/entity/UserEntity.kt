package com.trendsoftware.usermanagementapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.trendsoftware.usermanagementapp.domain.entity.Gender

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Gender
)
