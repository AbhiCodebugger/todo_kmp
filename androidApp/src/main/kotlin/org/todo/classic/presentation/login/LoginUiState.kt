package org.todo.classic.presentation.login

import org.todo.classic.domain.model.User

data class LoginUiState (
    val email: String = "",
    val emailError: String? = null,
    val password :String = "",
    val passError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null
)
