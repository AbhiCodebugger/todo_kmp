package org.todo.classic.domain.repository

import org.todo.classic.data.network.ApiResult
import org.todo.classic.domain.model.Session
import org.todo.classic.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): ApiResult<Session>
    suspend fun register(email: String, password: String)

    suspend fun getCurrentUser(): ApiResult<User>
}