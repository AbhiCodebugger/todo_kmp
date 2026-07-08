package org.todo.classic.presentation.register

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.usecase.RegisterUseCase
import org.todo.classic.domain.validation.ValidateEmailUseCase
import org.todo.classic.domain.validation.ValidateNameUseCase
import org.todo.classic.domain.validation.ValidatePasswordUseCase
import org.todo.classic.presentation.base.BasePresenter

class RegisterPresenter(
    private val registerUseCase: RegisterUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : BasePresenter() {

    private val _state = MutableStateFlow(RegisterState())
    val state :  StateFlow<RegisterState> = _state.asStateFlow()

    fun onEvent(event : RegisterEvent) {
        when(event) {
            is RegisterEvent.NameChanged -> {
                _state.value = _state.value.copy(
                    name = event.name,
                    nameError = null,
                    error = null
                )
            }
            is RegisterEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    emailError = null,
                    error = null
                )
            }
            is RegisterEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    passwordError = null,
                    error = null
                )
            }

            RegisterEvent.RegisterClicked -> {
                scope.launch {
                    register()
                }
            }
        }
    }

  private suspend fun register() {
        if (!validateInputs()) {
            return
        }
        _state.value = _state.value.copy(
            isLoading = true,
            error = null
        )

            when(
                val result = registerUseCase(
                    name = _state.value.name,
                    email = _state.value.email,
                    password = _state.value.password
                )
            ) {
                is ApiResult.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isRegistered = true
                    )
                }
                is ApiResult.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    private fun validateInputs(): Boolean {
        val nameResult = validateNameUseCase(_state.value.name)
        val emailResult = validateEmailUseCase(_state.value.email)
        val passwordResult = validatePasswordUseCase(_state.value.password)

        _state.value = _state.value.copy(
            nameError = nameResult.errorMessage,
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage
        )
        return nameResult.successful &&
                emailResult.successful &&
                passwordResult.successful
     }
}
