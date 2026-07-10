package org.todo.classic.presentation.startup

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.usecase.GetCurrentUserUseCase
import org.todo.classic.presentation.base.BasePresenter
import org.todo.classic.presentation.login.LoginState

class StartupPresenter(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) :  BasePresenter() {
    private val _state = MutableStateFlow(StartupState())
    val state : StateFlow<StartupState> = _state.asStateFlow()
    val currentState : StartupState get() = state.value

     fun onAppStarted() {
         scope.launch {
             when (getCurrentUserUseCase()) {
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
}