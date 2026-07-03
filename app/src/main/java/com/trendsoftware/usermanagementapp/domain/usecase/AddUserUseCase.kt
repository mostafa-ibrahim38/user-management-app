package com.trendsoftware.usermanagementapp.domain.usecase

import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.repository.UserRepository
import com.trendsoftware.usermanagementapp.domain.result.AddUserResult
import com.trendsoftware.usermanagementapp.domain.validation.ValidationResult
import javax.inject.Inject


class AddUserUseCase @Inject constructor(
    private val repository: UserRepository,
    private val validateUserUseCase: ValidateUserUseCase
) {
    suspend operator fun invoke(user: User): AddUserResult {
        return when (val validation = validateUserUseCase(user)) {
            ValidationResult.Success -> {
                try {
                    repository.addUser(user)
                    AddUserResult.Success
                } catch (e: Exception) {
                    AddUserResult.DatabaseError
                }
            }
            is ValidationResult.Error -> {
                AddUserResult.ValidationFailed(validation.error)
            }
        }
    }
}