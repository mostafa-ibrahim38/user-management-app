package com.trendsoftware.usermanagementapp.ui.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trendsoftware.usermanagementapp.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class DisplayViewModel @Inject constructor(
    getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DisplayUiState())
    val uiState: StateFlow<DisplayUiState> = _uiState.asStateFlow()

    init {
        getAllUsersUseCase()
            .onEach { users ->
                _uiState.update { it.copy(users = users, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }
}
