package com.trendsoftware.usermanagementapp.ui.input

import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.domain.validation.ValidationError

data class InputUiState(
    val name: String = "",
    val age: String = "",
    val jobTitle: String = "",
    val gender: Gender = Gender.UNKNOWN,
    val nameError: ValidationError? = null,
    val ageError: ValidationError? = null,
    val jobTitleError: ValidationError? = null,
    val genderError: ValidationError? = null,
    val databaseError: Boolean = false,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false
)
