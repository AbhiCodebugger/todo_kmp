package org.todo.classic.presentation.startup

sealed interface AuthenticationState {
    data object Loading : AuthenticationState
    data object Authenticated :  AuthenticationState
    data object Unauthenticated : AuthenticationState
}