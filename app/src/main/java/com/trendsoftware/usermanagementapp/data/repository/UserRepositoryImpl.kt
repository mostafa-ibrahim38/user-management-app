package com.trendsoftware.usermanagementapp.data.repository

import com.trendsoftware.usermanagementapp.data.local.dao.UserDao
import com.trendsoftware.usermanagementapp.data.mapper.toDomain
import com.trendsoftware.usermanagementapp.data.mapper.toEntity
import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun addUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao
            .getAllUsers()
            .map { users ->
                users.map { it.toDomain() }
            }
    }
}