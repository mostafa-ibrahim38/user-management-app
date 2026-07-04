package com.trendsoftware.usermanagementapp.ui.display

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PersonOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.trendsoftware.usermanagementapp.R
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.ui.components.UserListItem
import com.trendsoftware.usermanagementapp.ui.theme.UserManagementAppTheme

@Composable
fun DisplayScreen(
    onAddUserClicked: () -> Unit,
    viewModel: DisplayViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DisplayScreenContent(
        uiState = uiState,
        onAddUserClicked = onAddUserClicked
    )
}

@Composable
fun DisplayScreenContent(
    uiState: DisplayUiState,
    onAddUserClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_users),
                    style = MaterialTheme.typography.headlineSmall
                )
                if (uiState.users.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.users_count, uiState.users.size),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }


            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    uiState.users.isEmpty() -> {
                        EmptyState()
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                end = 16.dp,
                                top = 4.dp,
                                bottom = 96.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(
                                items = uiState.users,
                                key = { _, user -> user.id }
                            ) { index, user ->
                                UserListItem(user = user, index = index)
                            }
                        }
                    }
                }
            }
        }


        FloatingActionButton(
            onClick = onAddUserClicked,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.fab_add_user)
            )
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.PersonOff,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = stringResource(R.string.empty_state_title),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(R.string.empty_state_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Preview(showBackground = true, name = "Display Screen - With Users")
@Composable
private fun DisplayScreenWithUsersPreview() {
    UserManagementAppTheme {
        val sampleUsers = listOf(
            User(1, "أحمد محمد", 28, "مهندس برمجيات", Gender.MALE),
            User(2, "سارة أحمد", 26, "مصممة واجهات", Gender.FEMALE),
            User(3, "خالد حسن", 34, "مدير منتجات", Gender.MALE)
        )

        DisplayScreenContent(
            uiState = DisplayUiState(users = sampleUsers),
            onAddUserClicked = {}
        )
    }
}