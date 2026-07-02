package org.todo.classic.presentation.splash

import org.todo.classic.domain.model.User

data class SplashUiState(
    val isLoading: Boolean = true,
    val user: User? = null
)