package org.todo.classic.presentation.session

import org.todo.classic.domain.model.User

data class SessionState(
    val authenticationState: AuthenticationState =
        AuthenticationState.Loading,
    val user : User? = null
)