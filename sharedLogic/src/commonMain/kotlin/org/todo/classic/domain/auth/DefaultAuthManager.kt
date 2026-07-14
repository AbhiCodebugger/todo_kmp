package org.todo.classic.domain.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.model.Session
import org.todo.classic.session.SessionStorage

class DefaultAuthManager(
    private val sessionStorage: SessionStorage,
    private val tokenRefresher: TokenRefresher
): AuthManager {

    private val refreshMutex = Mutex()

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
        println("Saving Session $session")
        sessionStorage.save(session)
        _session.value = session
    }

    override suspend fun refreshToken(): ApiResult<Session> {

        return refreshMutex.withLock {
            val session = _session.value
                ?: return@withLock ApiResult.Error(
                    code = "NO_SESSION",
                    message = "No active session."
                )
            when (
                val result = tokenRefresher.refreshToken(
                    session.refreshToken
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
                    ApiResult.Success(updatedSession)
                }
            }
        }
    }

    override suspend fun logout() {
        sessionStorage.clear()
        _session.value = null
    }
}