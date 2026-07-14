package org.todo.classic.presentation.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.todo.classic.domain.model.User
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.usecase.GetCurrentUserUseCase
import org.todo.classic.domain.usecase.LogoutUseCase


class SessionPresenter(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase
) {
    private val _state = MutableStateFlow(SessionState())
    val state: StateFlow<SessionState> = _state.asStateFlow()

    suspend fun initialSession() {
        _state.value = _state.value.copy(authenticationState = AuthenticationState.Loading)

        when (val result = getCurrentUserUseCase()) {
            is ApiResult.Success -> onLoginSuccess(result.data)
            is ApiResult.Error -> {
                _state.value = SessionState(
                    authenticationState = AuthenticationState.Unauthenticated,
                    user = null
                )
            }
        }
    }

    fun  onLoginSuccess(user: User) {
        _state.value = SessionState(
            authenticationState = AuthenticationState.Authenticated,
            user = user
        )
    }

    suspend fun logout(){
        logoutUseCase()
        _state.value = SessionState(
            authenticationState = AuthenticationState.Unauthenticated,
            user = null
        )
    }
}
