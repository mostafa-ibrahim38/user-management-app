package com.trendsoftware.usermanagementapp.ui.input

import com.trendsoftware.usermanagementapp.domain.entity.Gender

data class InputUiState(
    val name: String = "",
    val age: String = "",
    val jobTitle: String = "",
    val gender: Gender = Gender.UNKNOWN,
    val nameError: String? = null,
    val ageError: String? = null,
    val jobTitleError: String? = null,
    val generalError: String? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false
)
