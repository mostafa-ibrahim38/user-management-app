package com.trendsoftware.usermanagementapp.data.repository

import com.google.common.truth.Truth.assertThat
import com.trendsoftware.usermanagementapp.data.local.dao.UserDao
import com.trendsoftware.usermanagementapp.data.local.entity.UserEntity
import com.trendsoftware.usermanagementapp.data.mapper.toEntity
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.domain.entity.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class UserRepositoryImplTest {

    private lateinit var dao: UserDao
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        dao = mockk(relaxed = true)
        repository = UserRepositoryImpl(dao)
    }

    @Test
    fun `given user when saveUser then dao insertUser is called with mapped entity`() = runTest {
        // Given
        val user = User(name = "Laila", age = 26, jobTitle = "PM", gender = Gender.FEMALE)
        //when
        repository.addUser(user)
        //Then
        coVerify(exactly = 1) { dao.insertUser(user.toEntity()) }
    }

    @Test
    fun `given entities in dao when getAllUsers then return mapped domain list`() = runTest {
        //Given
        val entities = listOf(
            UserEntity(
                id = 1,
                name = "Laila",
                age = 26,
                jobTitle = "PM",
                gender = Gender.FEMALE
            )
        )
        coEvery { dao.getAllUsers() } returns flowOf(entities)
        //When
        val result = repository.getAllUsers()
        //Then
        result.collect { users ->
            assertThat(users).containsExactly(
                User(
                    id = 1,
                    name = "Laila",
                    age = 26,
                    jobTitle = "PM",
                    gender = Gender.FEMALE
                )
            )
        }
    }
}
