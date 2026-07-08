package org.todo.classic.presentation.startup

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.usecase.GetCurrentUserUseCase

class StartupPresenter(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    private val _state = MutableStateFlow(StartupState())
    val state : StateFlow<StartupState> = _state.asStateFlow()

    suspend fun onAppStarted() {
        when(getCurrentUserUseCase()){
            is ApiResult.Success -> {
                _state.value = StartupState(
                    authenticationState = AuthenticationState.Authenticated
                )
            }
            is ApiResult.Error -> {
                _state.value = StartupState(
                    authenticationState = AuthenticationState.Unauthenticated
                )
            }
        }

    }
}