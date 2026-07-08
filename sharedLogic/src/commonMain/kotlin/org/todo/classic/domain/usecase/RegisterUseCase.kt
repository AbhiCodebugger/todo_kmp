package org.todo.classic.domain.usecase

import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.model.User
import org.todo.classic.domain.repository.AuthRepository

class RegisterUseCase( private val authRepository: AuthRepository) {

    suspend operator fun invoke(name: String, email: String, password: String)
      : ApiResult<User> {
        return authRepository.register(
            name = name,
            email = email,
            password = password
        )
    }
}