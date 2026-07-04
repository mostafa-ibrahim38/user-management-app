package com.trendsoftware.usermanagementapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.domain.entity.User
import com.trendsoftware.usermanagementapp.ui.theme.UserManagementAppTheme

@Composable
fun UserListItem(
    user: User,
    index: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatar(name = user.name, index = index)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainer,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "${user.age}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Text(
                text = user.jobTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            GenderTag(gender = user.gender)
        }

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
    }
}
@Preview(showBackground = true, name = "User List Item")
@Composable
private fun UserListItemPreview() {
    UserManagementAppTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            UserListItem(
                user = User(
                    name = "Ahmed Ibrahim",
                    jobTitle = "Software Engineer",
                    age = 28,
                    gender = Gender.MALE
                ),
                index = 0
            )

            Spacer(modifier = Modifier.height(12.dp))

            UserListItem(
                user = User(
                    name = "Sara Mohamed",
                    jobTitle = "UI/UX Designer",
                    age = 26,
                    gender = Gender.FEMALE
                ),
                index = 1
            )

            Spacer(modifier = Modifier.height(12.dp))

            UserListItem(
                user = User(
                    name = "Khaled Hassan",
                    jobTitle = "Product Manager",
                    age = 34,
                    gender = Gender.MALE
                ),
                index = 2
            )
        }
    }
}