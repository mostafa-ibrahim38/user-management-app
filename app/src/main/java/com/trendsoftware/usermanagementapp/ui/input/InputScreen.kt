package com.trendsoftware.usermanagementapp.ui.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.trendsoftware.usermanagementapp.R
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.domain.validation.ValidationError
import com.trendsoftware.usermanagementapp.ui.components.GenderOptionCard
import com.trendsoftware.usermanagementapp.ui.components.PrimaryActionButton
import com.trendsoftware.usermanagementapp.ui.components.UserTextField
import com.trendsoftware.usermanagementapp.ui.theme.UserManagementAppTheme

@Composable
fun InputScreen(
    onUserSaved: () -> Unit,
    viewModel: InputViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Listen to one-time events
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is UserInputEvent.UserSaved -> onUserSaved()
                is UserInputEvent.ShowValidationError -> {
                    // Handle validation errors if needed
                }
            }
        }
    }

    InputScreenContent(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
fun InputScreenContent(
    uiState: InputUiState,
    onAction: (UserInputAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = stringResource(R.string.title_registration),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(R.string.subtitle_add_new_member),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            UserTextField(
                value = uiState.name,
                onValueChange = { onAction(UserInputAction.NameChanged(it)) },
                label = stringResource(R.string.label_full_name),
                icon = Icons.Filled.Person,
                placeholder = stringResource(R.string.placeholder_full_name),
                errorText = uiState.nameError?.toErrorString()
            )

            UserTextField(
                value = uiState.age,
                onValueChange = { onAction(UserInputAction.AgeChanged(it)) },
                label = stringResource(R.string.label_age),
                icon = Icons.Filled.Cake,
                placeholder = stringResource(R.string.placeholder_age),
                errorText = uiState.ageError?.toErrorString(),
                keyboardType = KeyboardType.Number
            )

            UserTextField(
                value = uiState.jobTitle,
                onValueChange = { onAction(UserInputAction.JobTitleChanged(it)) },
                label = stringResource(R.string.label_job_title),
                icon = Icons.Filled.Work,
                placeholder = stringResource(R.string.placeholder_job_title),
                errorText = uiState.jobTitleError?.toErrorString()
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.label_gender),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    GenderOptionCard(
                        label = stringResource(R.string.gender_male),
                        selected = uiState.gender == Gender.MALE,
                        onClick = { onAction(UserInputAction.GenderChanged(Gender.MALE)) },
                        modifier = Modifier.weight(1f)
                    )
                    GenderOptionCard(
                        label = stringResource(R.string.gender_female),
                        selected = uiState.gender == Gender.FEMALE,
                        onClick = { onAction(UserInputAction.GenderChanged(Gender.FEMALE)) },
                        modifier = Modifier.weight(1f)
                    )
                }
                uiState.genderError?.let {
                    Text(
                        text = stringResource(R.string.error_gender_required),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                    )
                }
            }
        }

        if (uiState.databaseError) {
            Text(
                text = stringResource(R.string.error_database),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        PrimaryActionButton(
            text = stringResource(R.string.button_save),
            icon = Icons.Filled.Save,
            onClick = { onAction(UserInputAction.SaveClicked) },
            isLoading = uiState.isLoading
        )
    }
}

@Composable
private fun ValidationError.toErrorString(): String {
    return when (this) {
        ValidationError.EmptyName -> stringResource(R.string.error_name_required)
        ValidationError.InvalidAge -> stringResource(R.string.error_age_invalid)
        ValidationError.EmptyJobTitle -> stringResource(R.string.error_job_title_required)
        ValidationError.EmptyGender -> stringResource(R.string.error_gender_required)
    }
}

@Preview(showBackground = true, name = "Input Screen - Normal")
@Composable
private fun InputScreenPreview() {
    UserManagementAppTheme {
        InputScreenContent(
            uiState = InputUiState(
                name = "أحمد محمد",
                age = "28",
                jobTitle = "مهندس برمجيات",
                gender = Gender.MALE
            ),
            onAction = {}
        )
    }
}