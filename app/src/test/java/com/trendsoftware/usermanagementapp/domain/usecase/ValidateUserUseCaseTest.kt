package com.trendsoftware.usermanagementapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.validation.UserValidator
import com.trendsoftware.usermanagementapp.domain.validation.ValidationError
import com.trendsoftware.usermanagementapp.domain.validation.ValidationResult
import org.junit.Before
import org.junit.Test

class ValidateUserUseCaseTest {

    private lateinit var validateUserUseCase: ValidateUserUseCase
    private val validUser =
        User(
            name = "Hana",
            age = 27,
            jobTitle = "Android Developer",
            gender = Gender.MALE
        )

    @Before
    fun setUp() {
        validateUserUseCase = ValidateUserUseCase(
            validator = UserValidator()
        )
    }

    @Test
    fun `should return success when user is valid`() {

        // When
        val result = validateUserUseCase(validUser)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Success
        )
    }

    @Test
    fun `should return empty name error when name is blank`() {
        // Given
        val invalidUser = validUser.copy(name = "")

        // When
        val result = validateUserUseCase(invalidUser)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(
                ValidationError.EmptyName
            )
        )
    }

    @Test
    fun `should return invalid age error when age is less than or equal zero`() {
        // Given
        val invalidUser = validUser.copy(age = 0)


        // When
        val result = validateUserUseCase(invalidUser)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(
                ValidationError.InvalidAge
            )
        )
    }

    @Test
    fun `should return invalid age error when age is greater than max age`() {
        // Given
        val user = User(
            id = 0,
            name = "Ahmed",
            age = 121,
            jobTitle = "Android Developer",
            gender = Gender.MALE
        )

        // When
        val result = validateUserUseCase(user)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(
                ValidationError.InvalidAge
            )
        )
    }

    @Test
    fun `should return empty job title error when job title is blank`() {
        // Given
        val invalidUser = validUser.copy(jobTitle = "")


        // When
        val result = validateUserUseCase(invalidUser)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(
                ValidationError.EmptyJobTitle
            )
        )
    }

    @Test
    fun `should return first validation error when user has multiple invalid fields`() {
        // Given
        val invalidUser = validUser.copy(name = "", age = -1, jobTitle = "")


        // When
        val result = validateUserUseCase(invalidUser)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(
                ValidationError.EmptyName
            )
        )
    }
}