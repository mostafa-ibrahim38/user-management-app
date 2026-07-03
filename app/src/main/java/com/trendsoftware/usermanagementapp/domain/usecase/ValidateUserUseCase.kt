package com.trendsoftware.usermanagementapp.domain.usecase

import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.validation.UserValidator
import com.trendsoftware.usermanagementapp.domain.validation.ValidationResult
import javax.inject.Inject

class ValidateUserUseCase @Inject constructor(
    private val validator: UserValidator
) {
    operator fun invoke(user: User): ValidationResult {
        listOf(
            validator.validateName(user.name),
            validator.validateAge(user.age),
            validator.validateJobTitle(user.jobTitle),
            validator.validateGender(user.gender)
        ).forEach { result ->
            if (result is ValidationResult.Error) {
                return result
            }
        }
        return ValidationResult.Success
    }
}
