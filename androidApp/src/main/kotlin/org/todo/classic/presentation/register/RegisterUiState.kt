package org.todo.classic.presentation.register

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",

    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,

    val error: String? = null
)