package org.todo.classic.presentation.login

import org.todo.classic.domain.model.User

data class LoginState (
    val email: String = "",
    val emailError: String? = null,
    val password :String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null
)