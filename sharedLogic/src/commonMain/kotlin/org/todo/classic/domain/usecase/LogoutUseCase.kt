package org.todo.classic.domain.usecase

import org.todo.classic.domain.auth.AuthManager

class LogoutUseCase(
    private val authManager: AuthManager
) {
    suspend operator fun invoke() {
        authManager.logout()
    }
}