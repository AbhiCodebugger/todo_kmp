package org.todo.classic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import org.todo.classic.presentation.session.SessionViewModel
import org.todo.classic.presentation.dashboard.DashboardScreen
import org.todo.classic.presentation.login.LoginScreen
import org.todo.classic.presentation.register.RegisterScreen
import org.todo.classic.presentation.session.AuthenticationState
import org.todo.classic.presentation.startup.SplashScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val sessionViewModel: SessionViewModel = koinViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                sessionViewModel = sessionViewModel,
                onNavigate = { authState ->
                    when (authState) {
                        AuthenticationState.Authenticated -> {
                            navController.navigate(Screen.Dashboard.route) {
                                popUpTo(Screen.Splash.route) { inclusive = true }
                            }
                        }

                        AuthenticationState.Unauthenticated -> {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Splash.route) { inclusive = true }
                            }
                        }
                        AuthenticationState.Loading -> Unit
                    }
                }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
                )
        }
        composable(Screen.Register.route){
            RegisterScreen(
                navController = navController,

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