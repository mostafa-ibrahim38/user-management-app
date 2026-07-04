package com.trendsoftware.usermanagementapp.data.local.converter

import androidx.room.TypeConverter
import com.trendsoftware.usermanagementapp.domain.entity.Gender

class GenderConverter {

    @TypeConverter
    fun fromGender(gender: Gender): String =
        gender.name

    @TypeConverter
    fun toGender(value: String): Gender =
        Gender.valueOf(value)
}