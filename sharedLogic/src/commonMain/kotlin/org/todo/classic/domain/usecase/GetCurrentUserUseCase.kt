package org.todo.classic.domain.usecase

import org.todo.classic.data.network.ApiResult
import org.todo.classic.domain.model.User
import org.todo.classic.domain.repository.AuthRepository

class GetCurrentUserUseCase (private val repository: AuthRepository){
    suspend operator fun invoke(): ApiResult<User> {
        return repository.getCurrentUser()
    }
}