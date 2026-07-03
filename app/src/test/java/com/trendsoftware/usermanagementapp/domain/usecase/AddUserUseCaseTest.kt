package com.trendsoftware.usermanagementapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.repository.UserRepository
import com.trendsoftware.usermanagementapp.domain.result.AddUserResult
import com.trendsoftware.usermanagementapp.domain.validation.UserValidator
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddUserUseCaseTest {

    private lateinit var repository: UserRepository
    private lateinit var addUserUseCase: AddUserUseCase
    private val validUser =
        User(
            name = "Hana",
            age = 27,
            jobTitle = "Android Developer",
            gender = Gender.MALE
        )
    @Before
    fun setUp() {
        repository = mockk(relaxed = true)

        addUserUseCase = AddUserUseCase(
            repository = repository,
            validateUserUseCase = ValidateUserUseCase(
                validator = UserValidator()
            )
        )
    }

    @Test
    fun `should save user and return success when user is valid`() = runTest {
        // Given
        coEvery { repository.addUser(validUser) } returns Unit

        // When
        val result = addUserUseCase(validUser)

        // Then
        assertThat(result).isEqualTo(AddUserResult.Success)

        coVerify(exactly = 1) {
            repository.addUser(validUser)
        }

        confirmVerified(repository)
    }

    @Test
    fun `should return validation error and never save user when name is empty`() = runTest {
        // Given
        val invalidUser = validUser.copy(name = "")


        // When
        val result = addUserUseCase(invalidUser)

        // Then
        assertThat(result).isInstanceOf(AddUserResult.ValidationFailed::class.java)

        coVerify(exactly = 0) {
            repository.addUser(any())
        }

        confirmVerified(repository)
    }

    @Test
    fun `should return validation error and never save user when age is invalid`() = runTest {
        // Given
        val invalidUser = validUser.copy(age = -1)

        // When
        val result = addUserUseCase(invalidUser)

        // Then
        assertThat(result).isInstanceOf(AddUserResult.ValidationFailed::class.java)

        coVerify(exactly = 0) {
            repository.addUser(any())
        }

        confirmVerified(repository)
    }

    @Test
    fun `should return validation error and never save user when job title is empty`() = runTest {
        // Given
        val invalidUser = validUser.copy(jobTitle = "")

        // When
        val result = addUserUseCase(invalidUser)

        // Then
        assertThat(result).isInstanceOf(AddUserResult.ValidationFailed::class.java)

        coVerify(exactly = 0) {
            repository.addUser(any())
        }

        confirmVerified(repository)
    }

    @Test
    fun `should return database error when repository throws exception`() = runTest {
        // Given
        coEvery {
            repository.addUser(validUser)
        } throws RuntimeException("Database Error")

        // When
        val result = addUserUseCase(validUser)

        // Then
        assertThat(result).isEqualTo(AddUserResult.DatabaseError)

        coVerify(exactly = 1) {
            repository.addUser(validUser)
        }

        confirmVerified(repository)
    }
}