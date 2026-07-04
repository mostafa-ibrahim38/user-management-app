package com.trendsoftware.usermanagementapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.trendsoftware.usermanagementapp.ui.components.AppBottomNavBar
import com.trendsoftware.usermanagementapp.ui.components.BottomNavItem
import com.trendsoftware.usermanagementapp.ui.display.DisplayScreen
import com.trendsoftware.usermanagementapp.ui.input.InputScreen


private val bottomNavItems = listOf(
    BottomNavItem(label = "التسجيل", icon = Icons.Filled.PersonAdd, route = Screen.Input.route),
    BottomNavItem(label = "المستخدمين", icon = Icons.Filled.Group, route = Screen.Display.route)
)

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            AppBottomNavBar(
                items = bottomNavItems,
                currentRoute = currentRoute,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Input.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Input.route) {
                InputScreen(
                    onUserSaved = {
                        navController.navigate(Screen.Display.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Screen.Display.route) {
                DisplayScreen(
                    onAddUserClicked = {
                        navController.navigate(Screen.Input.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
