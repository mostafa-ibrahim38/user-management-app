package com.trendsoftware.usermanagementapp.domain.validation

import com.trendsoftware.usermanagementapp.domain.entity.Gender

class UserValidator {
    fun validateName(name: String): ValidationResult {
        return if (name.isBlank()) {
            ValidationResult.Error(ValidationError.EmptyName)
        } else {
            ValidationResult.Success
        }
    }

    fun validateAge(age: Int): ValidationResult {
        return when {
            age <= 0 -> ValidationResult.Error(ValidationError.InvalidAge)
            age > 120 -> ValidationResult.Error(ValidationError.InvalidAge)
            else -> ValidationResult.Success
        }
    }

    fun validateJobTitle(jobTitle: String): ValidationResult {
        return if (jobTitle.isBlank()) {
            ValidationResult.Error(ValidationError.EmptyJobTitle)
        } else {
            ValidationResult.Success
        }
    }

    fun validateGender(gender: Gender): ValidationResult {
        return if (gender == Gender.UNKNOWN) {
            ValidationResult.Error(ValidationError.EmptyGender)
        } else {
            ValidationResult.Success
        }
    }
}