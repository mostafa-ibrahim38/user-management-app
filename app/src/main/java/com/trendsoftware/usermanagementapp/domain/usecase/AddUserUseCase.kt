package com.trendsoftware.usermanagementapp.domain.usecase

import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.repository.UserRepository
import com.trendsoftware.usermanagementapp.domain.result.AddUserResult


class AddUserUseCase(
    private val repository: UserRepository,
    private val validateUserUseCase: ValidateUserUseCase
) {
    suspend operator fun invoke(user: User): AddUserResult {
        TODO("Not yet implemented")
    }
}
