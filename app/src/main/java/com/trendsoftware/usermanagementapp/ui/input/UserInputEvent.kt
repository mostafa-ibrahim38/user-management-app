package com.trendsoftware.usermanagementapp.ui.input

sealed interface UserInputEvent {

    data object UserSaved : UserInputEvent
}