package com.trendsoftware.usermanagementapp.domain.validation

import com.google.common.truth.Truth.assertThat
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import org.junit.Before
import org.junit.Test

class UserValidatorTest {

    private lateinit var userValidation: UserValidator

    @Before
    fun setUp() {
        userValidation = UserValidator()
    }

    //region Validate Name

    @Test
    fun `should return false when name is empty`() {
        // Given
        val name = ""

        // When
        val result = userValidation.validateName(name)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(ValidationError.EmptyName)
        )
    }

    @Test
    fun `should return false when name is blank`() {
        // Given
        val name = "   "

        // When
        val result = userValidation.validateName(name)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(ValidationError.EmptyName)
        )
    }

    @Test
    fun `should return true when name is valid`() {
        // Given
        val name = "Ahmed"

        // When
        val result = userValidation.validateName(name)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Success
        )
    }

    //endregion

    //region Validate Age

    @Test
    fun `should return false when age is negative`() {
        // Given
        val age = -1

        // When
        val result = userValidation.validateAge(age)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(ValidationError.InvalidAge)
        )
    }

    @Test
    fun `should return error when age is zero`() {
        // Given
        val age = 0

        // When
        val result = userValidation.validateAge(age)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(ValidationError.InvalidAge)
        )
    }

    @Test
    fun `should return success when age is one (minimum valid age)`() {
        // Given
        val age = 1

        // When
        val result = userValidation.validateAge(age)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Success
        )
    }

    @Test
    fun `should return true when age is positive`() {
        // Given
        val age = 25

        // When
        val result = userValidation.validateAge(age)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Success
        )
    }

    //endregion

    //region Validate Job Title

    @Test
    fun `should return false when jobTitle is empty`() {
        // Given
        val jobTitle = ""

        // When
        val result = userValidation.validateJobTitle(jobTitle)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(ValidationError.EmptyJobTitle)
        )
    }

    @Test
    fun `should return false when jobTitle is blank`() {
        // Given
        val jobTitle = "   "

        // When
        val result = userValidation.validateJobTitle(jobTitle)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(ValidationError.EmptyJobTitle)
        )
    }

    @Test
    fun `should return true when jobTitle is valid`() {
        // Given
        val jobTitle = "Software Engineer"

        // When
        val result = userValidation.validateJobTitle(jobTitle)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Success
        )
    }

    //endregion

    //region Validate Gender

    @Test
    fun `should return true when gender is valid`() {
        // Given
        val gender = Gender.MALE

        // When
        val result = userValidation.validateGender(gender)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Success
        )
    }

    @Test
    fun `should return false when gender is empty`() {
        // Given
        val gender = Gender.UNKNOWN

        // When
        val result = userValidation.validateGender(gender)

        // Then
        assertThat(result).isEqualTo(
            ValidationResult.Error(ValidationError.EmptyGender)
        )
    }

    //endregion
}