package org.todo.classic.domain.usecase

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.clearAuthTokens
import org.todo.classic.domain.auth.AuthManager

class LogoutUseCase(
    private val authManager: AuthManager,
    private val httpClient: HttpClient
) {
    suspend operator fun invoke() {
        authManager.logout()
        httpClient.clearAuthTokens()
    }
}