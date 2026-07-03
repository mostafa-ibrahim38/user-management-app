package com.trendsoftware.usermanagementapp.data.repository

import com.trendsoftware.usermanagementapp.data.local.dao.UserDao
import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun addUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}