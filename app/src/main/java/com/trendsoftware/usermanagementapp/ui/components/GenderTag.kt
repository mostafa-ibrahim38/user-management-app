package com.trendsoftware.usermanagementapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.ui.theme.UserManagementAppTheme

@Composable
fun GenderTag(gender: Gender) {
    val icon = if (gender == Gender.MALE) Icons.Filled.Male else Icons.Filled.Female
    val label = if (gender == Gender.MALE) "ذكر" else "أنثى"

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(showBackground = true, name = "Gender Tag")
@Composable
private fun GenderTagPreview() {
    UserManagementAppTheme {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            GenderTag(gender = Gender.MALE)

            Spacer(modifier = Modifier.height(16.dp))

            GenderTag(gender = Gender.FEMALE)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(12.dp)
            ) {
                GenderTag(gender = Gender.MALE)
            }
        }
    }
}