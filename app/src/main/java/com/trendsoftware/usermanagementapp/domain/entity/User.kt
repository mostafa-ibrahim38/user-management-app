package com.trendsoftware.usermanagementapp.domain.entity

data class User (
    val id: Long = 0L,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Gender
)