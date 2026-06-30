package org.todo.classic.presentation.Session

import org.todo.classic.domain.model.User

data class SessionState(
    val isLoading: Boolean = true,
    val isLoggedIn: Boolean = false,
    val user : User? = null
)
