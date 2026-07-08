package org.todo.classic.presentation.startup

data class StartupState(
   val authenticationState : AuthenticationState =
      AuthenticationState.Loading
)