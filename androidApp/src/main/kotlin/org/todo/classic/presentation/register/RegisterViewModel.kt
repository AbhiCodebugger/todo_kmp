package org.todo.classic.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.todo.classic.data.network.ApiResult
import org.todo.classic.domain.usecase.RegisterUseCase
import org.todo.classic.domain.validation.ValidateEmailUseCase
import org.todo.classic.domain.validation.ValidateNameUseCase
import org.todo.classic.domain.validation.ValidatePasswordUseCase

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChanged(name: String){
        _uiState.value = _uiState.value.copy(
            name = name,
            error = null
        )
    }
    fun onEmailChanged(email: String){
        _uiState.value = _uiState.value.copy(
            email = email,
            error = null
        )
    }
    fun onPasswordChanged(password: String){
        _uiState.value = _uiState.value.copy(
            password = password,
            error = null
        )
    }

    fun register() {
        if (!validateInputs()) {
            return
        }
            viewModelScope.launch {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)

                when(
                    val result = registerUseCase(
                        name = _uiState.value.name,
                        email = _uiState.value.email,
                        password = _uiState.value.password
                    )
                ) {
                    is ApiResult.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            isRegistered = true
                        )
                    }
                    is ApiResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
            }

        }
    }

    private fun validateInputs(): Boolean {
        val nameResult = validateNameUseCase(_uiState.value.name)
        val emailResult = validateEmailUseCase(_uiState.value.email)
        val passwordResult = validatePasswordUseCase(_uiState.value.password)

       _uiState.value = _uiState.value.copy(
           nameError = nameResult.errorMessage,
           emailError = emailResult.errorMessage,
           passwordError = passwordResult.errorMessage
       )
        return nameResult.successful && emailResult.successful && passwordResult.successful
    }

}
