package com.trendsoftware.usermanagementapp.ui.display

import com.trendsoftware.usermanagementapp.domain.entity.User


data class DisplayUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = true
)
