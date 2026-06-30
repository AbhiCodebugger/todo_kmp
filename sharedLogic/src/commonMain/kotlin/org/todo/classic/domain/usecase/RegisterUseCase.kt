package org.todo.classic.domain.usecase

import org.todo.classic.domain.repository.AuthRepository

class RegisterUseCase( private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String)
     {
        return authRepository.register(email, password)
    }
}