package org.todo.classic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import org.todo.classic.presentation.Session.SessionViewModel
import org.todo.classic.presentation.dashboard.DashboardScreen
import org.todo.classic.presentation.login.LoginScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val sessionViewModel: SessionViewModel = koinViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
                )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
            )
        }
    }
}