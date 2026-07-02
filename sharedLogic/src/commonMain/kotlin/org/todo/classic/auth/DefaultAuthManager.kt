package org.todo.classic.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.todo.classic.data.network.ApiResult
import org.todo.classic.data.repository.TokenRefresher
import org.todo.classic.domain.model.Session
import org.todo.classic.session.SessionStorage

class DefaultAuthManager(
    private val sessionStorage: SessionStorage,
    private val tokenRefresher: TokenRefresher
): AuthManager {
    private val _session = MutableStateFlow<Session?>(null)

    override val session: StateFlow<Session?>
        get() = _session.asStateFlow()

    init {
        _session.value = sessionStorage.get()
    }

    override fun currentSession(): Session? {
        return _session.value
    }

    override fun saveSession(session: Session){
        sessionStorage.save(session)
        _session.value = session
    }

    override suspend fun refreshToken(): ApiResult<Unit> {
        val session = _session.value
            ?: return ApiResult.Error(
            message = "No active session."
        )
        return when (
            val result = tokenRefresher.refreshToken(session.refreshToken
            )) {
                is ApiResult.Error -> {
                logout()
                ApiResult.Error(
                    code = result.code,
                    message = result.message
                )
            }
                is ApiResult.Success -> {
                val updatedSession = session.copy(
                    accessToken = result.data
                )
                saveSession(updatedSession)
                ApiResult.Success(Unit)

            }
        }
    }

    override suspend fun logout() {
        sessionStorage.clear()
        _session.value = null
    }
}