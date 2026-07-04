package com.trendsoftware.usermanagementapp.domain.validation

sealed class ValidationResult {
    object Success : ValidationResult()

    data class Error(
        val error: ValidationError
    ) : ValidationResult()
}