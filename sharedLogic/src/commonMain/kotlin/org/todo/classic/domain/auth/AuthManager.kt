package org.todo.classic.domain.auth

import kotlinx.coroutines.flow.StateFlow
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.model.Session

interface AuthManager {
    val session: StateFlow<Session?>
    suspend fun refreshToken(): ApiResult<Session>
    suspend fun logout()
    fun currentSession() : Session?

    fun saveSession(session: Session)
}