package com.trendsoftware.usermanagementapp.ui.navigation


sealed class Screen(val route: String) {
    data object Input : Screen("input_screen")
    data object Display : Screen("display_screen")
}
