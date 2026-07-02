package org.todo.classic.auth

import kotlinx.coroutines.flow.StateFlow
import org.todo.classic.data.network.ApiResult
import org.todo.classic.domain.model.Session

interface AuthManager {
    val session: StateFlow<Session?>
    suspend fun refreshToken(): ApiResult<Unit>
    suspend fun logout()
    fun currentSession() : Session?

    fun saveSession(session: Session)
}