package com.trendsoftware.usermanagementapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trendsoftware.usermanagementapp.data.local.converter.GenderConverter
import com.trendsoftware.usermanagementapp.data.local.dao.UserDao
import com.trendsoftware.usermanagementapp.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenderConverter::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}