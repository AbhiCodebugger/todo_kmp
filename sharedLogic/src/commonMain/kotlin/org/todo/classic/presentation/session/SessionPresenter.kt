package org.todo.classic.presentation.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.todo.classic.domain.model.User
import org.todo.classic.domain.usecase.LogoutUseCase
import org.todo.classic.presentation.startup.AuthenticationState


class SessionPresenter(
    private val logoutUseCase: LogoutUseCase
) {
    private val _state = MutableStateFlow(SessionState())
    val state: StateFlow<SessionState> = _state.asStateFlow()

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
