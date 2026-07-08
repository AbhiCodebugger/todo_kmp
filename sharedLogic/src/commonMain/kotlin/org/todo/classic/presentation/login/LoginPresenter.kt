package org.todo.classic.presentation.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.usecase.GetCurrentUserUseCase
import org.todo.classic.domain.usecase.LoginUseCase
import org.todo.classic.domain.validation.ValidateEmailUseCase
import org.todo.classic.domain.validation.ValidatePasswordUseCase
import org.todo.classic.presentation.base.BasePresenter

class LoginPresenter(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
) : BasePresenter() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

     fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    emailError = null,
                    error = null
                )
            }

            is LoginEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    passwordError = null,
                    error = null
                )
            }

            LoginEvent.LoginClicked -> {
                scope.launch {
                    login()
                }
            }
        }
    }

    private suspend fun login() {
        if (!validateInputs()) return

        _state.value = _state.value.copy(
            isLoading = true,
            error = null
        )

        when (val loginResult = loginUseCase(
            email = _state.value.email,
            password = _state.value.password
        )) {
            is ApiResult.Error -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = loginResult.message
                )
            }

            is ApiResult.Success -> {
                when (val userResult = getCurrentUserUseCase()) {
                    is ApiResult.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = userResult.message
                        )
                    }

                    is ApiResult.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            user = userResult.data
                        )
                    }
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val emailResult = validateEmailUseCase(_state.value.email)
        val passwordResult = validatePasswordUseCase(_state.value.password)

        _state.value = _state.value.copy(
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage
        )

        return emailResult.successful && passwordResult.successful
    }
}
