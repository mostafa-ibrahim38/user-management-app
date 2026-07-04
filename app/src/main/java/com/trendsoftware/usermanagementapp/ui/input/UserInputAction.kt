package com.trendsoftware.usermanagementapp.ui.input

import com.trendsoftware.usermanagementapp.domain.entity.Gender

sealed interface UserInputAction {

    data class NameChanged(
        val value: String
    ) : UserInputAction

    data class AgeChanged(
        val value: String
    ) : UserInputAction

    data class JobTitleChanged(
        val value: String
    ) : UserInputAction

    data class GenderChanged(
        val value: Gender
    ) : UserInputAction

    data object SaveClicked : UserInputAction
}