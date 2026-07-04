package com.trendsoftware.usermanagementapp.di

import android.content.Context
import androidx.room.Room
import com.trendsoftware.usermanagementapp.data.local.dao.UserDao
import com.trendsoftware.usermanagementapp.data.local.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(
        database: UserDatabase
    ): UserDao {
        return database.userDao()
    }
}