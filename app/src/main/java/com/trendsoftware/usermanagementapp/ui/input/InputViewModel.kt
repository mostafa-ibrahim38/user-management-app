package com.trendsoftware.usermanagementapp.ui.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.domain.result.AddUserResult
import com.trendsoftware.usermanagementapp.domain.usecase.AddUserUseCase
import com.trendsoftware.usermanagementapp.domain.validation.ValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(InputUiState())
    val uiState: StateFlow<InputUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<UserInputEvent>()
    val events: SharedFlow<UserInputEvent> = _events.asSharedFlow()

    fun onAction(action: UserInputAction) {
        when (action) {
            is UserInputAction.NameChanged -> {
                _uiState.update {
                    it.copy(name = action.value, nameError = null, generalError = null)
                }
            }
            is UserInputAction.AgeChanged -> {
                _uiState.update {
                    it.copy(age = action.value, ageError = null, generalError = null)
                }
            }
            is UserInputAction.JobTitleChanged -> {
                _uiState.update {
                    it.copy(jobTitle = action.value, jobTitleError = null, generalError = null)
                }
            }
            is UserInputAction.GenderChanged -> {
                _uiState.update { it.copy(gender = action.value) }
            }
            is UserInputAction.SaveClicked -> saveUser()
        }
    }

    private fun saveUser() {
        val current = _uiState.value

        val user = User(
            name = current.name.trim(),
            age = current.age.toIntOrNull() ?: 0,
            jobTitle = current.jobTitle.trim(),
            gender = current.gender
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, generalError = null) }

            when (val result = addUserUseCase(user)) {
                is AddUserResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _events.emit(UserInputEvent.UserSaved)
                }
                is AddUserResult.ValidationFailed -> {
                    _uiState.update {
                        it.copy(isLoading = false).applyValidationError(result.error)
                    }
                }
                is AddUserResult.DatabaseError -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            generalError = "حدث خطأ أثناء الحفظ، يرجى المحاولة مرة أخرى"
                        )
                    }
                }
            }
        }
    }

    private fun InputUiState.applyValidationError(error: ValidationError): InputUiState {
        return when (error) {
            ValidationError.EmptyName -> copy(nameError = "الاسم مطلوب ولا يمكن أن يكون فارغًا")
            ValidationError.InvalidAge -> copy(ageError = "يرجى إدخال عمر صحيح")
            ValidationError.EmptyJobTitle -> copy(jobTitleError = "المسمى الوظيفي مطلوب")
            ValidationError.EmptyGender -> copy(generalError = "يرجى اختيار النوع")
        }
    }
}