package org.todo.classic.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.todo.classic.domain.usecase.GetCurrentUserUseCase
import org.todo.classic.domain.usecase.LoginUseCase
import org.todo.classic.domain.validation.ValidateEmailUseCase
import org.todo.classic.domain.validation.ValidatePasswordUseCase
import org.todo.classic.data.network.ApiResult


class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    ) : ViewModel(){
    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String){
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null
        )
    }
    fun onPasswordChanged(password: String){
        _uiState.value = _uiState.value.copy(
            password = password,
            passError = null
        )
    }
    fun login() {
        if(!validateInputs()){
            return
        }
        viewModelScope.launch {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    error = null
                )
                when(val loginResult = loginUseCase(
                    email = _uiState.value.email,
                    password = _uiState.value.password
                    )) {
                    is ApiResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = loginResult.message
                        )
                    }
                    is ApiResult.Success -> {
                        when(val userResult = getCurrentUserUseCase()){
                            is ApiResult.Error -> {
                                _uiState.value = _uiState.value.copy(
                                    isLoading = false,
                                    error = userResult.message
                                )
                            }
                            is ApiResult.Success -> {
                                _uiState.value = _uiState.value.copy(
                                    isLoading  = false,
                                    user = userResult.data
                                )
                            }
                        }
                    }
            }
        }
    }


    private fun validateInputs(): Boolean {
       val emailResult = validateEmailUseCase(uiState.value.email)
        val passwordResult = validatePasswordUseCase(uiState.value.password)

        _uiState.value = _uiState.value.copy(
            emailError = emailResult.errorMessage,
            passError = passwordResult.errorMessage
        )
        return emailResult.successful && passwordResult.successful

    }
}