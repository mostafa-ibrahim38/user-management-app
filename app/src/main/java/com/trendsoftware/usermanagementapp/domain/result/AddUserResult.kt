package com.trendsoftware.usermanagementapp.domain.result

import com.trendsoftware.usermanagementapp.domain.validation.ValidationError

sealed interface AddUserResult {
    data object Success : AddUserResult
    data class ValidationFailed(val error: ValidationError) : AddUserResult
    data object DatabaseError : AddUserResult
}
