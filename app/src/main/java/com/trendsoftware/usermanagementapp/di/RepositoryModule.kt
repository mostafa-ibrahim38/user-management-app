package com.trendsoftware.usermanagementapp.di

import com.trendsoftware.usermanagementapp.data.repository.UserRepositoryImpl
import com.trendsoftware.usermanagementapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        repository: UserRepositoryImpl
    ): UserRepository
}