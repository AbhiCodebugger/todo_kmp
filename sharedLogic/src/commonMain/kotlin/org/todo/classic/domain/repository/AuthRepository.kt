package org.todo.classic.domain.repository

import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.model.Session
import org.todo.classic.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): ApiResult<Session>
    suspend fun register(name: String, email: String, password: String): ApiResult<User>
    suspend fun getCurrentUser(): ApiResult<User>
}