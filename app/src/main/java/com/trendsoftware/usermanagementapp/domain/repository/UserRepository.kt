package com.trendsoftware.usermanagementapp.domain.repository

import com.trendsoftware.usermanagementapp.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun addUser(user: User)
    fun getAllUsers(): Flow<List<User>>
}