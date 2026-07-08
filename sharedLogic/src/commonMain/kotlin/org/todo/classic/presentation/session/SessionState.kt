package org.todo.classic.presentation.session

import org.todo.classic.domain.model.User
import org.todo.classic.presentation.startup.AuthenticationState

data class SessionState(
    val authenticationState: AuthenticationState =
        AuthenticationState.Unauthenticated,
    val user : User? = null
)