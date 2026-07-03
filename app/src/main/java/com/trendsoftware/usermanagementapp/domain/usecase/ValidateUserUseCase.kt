package com.trendsoftware.usermanagementapp.domain.usecase

import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.validation.UserValidator
import com.trendsoftware.usermanagementapp.domain.validation.ValidationResult

class ValidateUserUseCase(
    private val validator: UserValidator
) {
    operator fun invoke(user: User): ValidationResult {
        TODO("Not yet implemented")
    }
}
