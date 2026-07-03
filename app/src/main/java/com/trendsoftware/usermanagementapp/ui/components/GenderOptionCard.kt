package com.trendsoftware.usermanagementapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trendsoftware.usermanagementapp.ui.theme.UserManagementAppTheme

@Composable
fun GenderOptionCard(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = if (selected) {
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.10f)
                } else {
                    MaterialTheme.colorScheme.surfaceContainer
                },
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .selectable(selected = selected, onClick = onClick, role = Role.RadioButton)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
@Preview(showBackground = true, name = "Gender Option Card")
@Composable
private fun GenderOptionCardPreview() {
    UserManagementAppTheme {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            GenderOptionCard(
                label = "Male",
                selected = true,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            GenderOptionCard(
                label = "Female",
                selected = false,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            GenderOptionCard(
                label = "Other",
                selected = false,
                onClick = {}
            )
        }
    }
}