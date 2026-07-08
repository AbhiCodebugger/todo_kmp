package org.todo.classic.domain.usecase

import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.model.Session
import org.todo.classic.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): ApiResult<Session> {
        return authRepository.login(
            email, password
        )
    }
}