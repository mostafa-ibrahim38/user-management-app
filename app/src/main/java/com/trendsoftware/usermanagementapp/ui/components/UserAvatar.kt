package com.trendsoftware.usermanagementapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.trendsoftware.usermanagementapp.ui.theme.UserManagementAppTheme

private val avatarPalette = listOf(
    Color(0xFF0D47A1),
    Color(0xFFDCDDDD),
    Color(0xFF004E85),
    Color(0xFFC3C6D4)
)

@Composable
fun UserAvatar(
    name: String,
    index: Int = 0,
    size: Dp = 48.dp
) {
    val backgroundColor = avatarPalette[index % avatarPalette.size]
    val initial = name.trim().firstOrNull()?.uppercase() ?: "?"

    Box(
        modifier = Modifier
            .size(size)
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true, name = "User Avatar")
@Composable
private fun UserAvatarPreview() {
    UserManagementAppTheme {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Different sizes and names
            UserAvatar(name = "Ahmed Ibrahim", index = 0, size = 64.dp)

            Spacer(modifier = Modifier.height(16.dp))

            UserAvatar(name = "Sara Ahmed", index = 1, size = 48.dp)

            Spacer(modifier = Modifier.height(16.dp))

            UserAvatar(name = "Mohamed Ali", index = 2, size = 40.dp)

            Spacer(modifier = Modifier.height(16.dp))

            UserAvatar(name = "Fatima", index = 3, size = 56.dp)

            Spacer(modifier = Modifier.height(16.dp))

            UserAvatar(name = "?", index = 0, size = 32.dp)
        }
    }
}