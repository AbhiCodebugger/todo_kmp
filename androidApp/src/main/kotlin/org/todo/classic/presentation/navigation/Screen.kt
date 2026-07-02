package org.todo.classic.presentation.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register: Screen("register")
    data object Dashboard : Screen("dashboard")
}