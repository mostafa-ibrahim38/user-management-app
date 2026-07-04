package com.trendsoftware.usermanagementapp.ui.input

import com.trendsoftware.usermanagementapp.domain.validation.ValidationError

sealed interface UserInputEvent {

    data object UserSaved : UserInputEvent

    data class ShowValidationError(
        val error: ValidationError
    ) : UserInputEvent
}