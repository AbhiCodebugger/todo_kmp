package org.todo.classic.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.todo.classic.data.network.ApiResult
import org.todo.classic.domain.usecase.GetCurrentUserUseCase
import org.todo.classic.session.SessionStorage

class SplashViewModel(
    private val sessionStorage: SessionStorage,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        checkSession()
    }

    private fun checkSession(){
        viewModelScope.launch {
            val session = sessionStorage.get()
            if(session == null){
                _uiState.value = SplashUiState(
                    isLoading = false
                )
                return@launch
            }
            when(val result = getCurrentUserUseCase()) {
                is ApiResult.Success -> {
                    _uiState.value = SplashUiState(
                        isLoading = false,
                        user = result.data
                    )
                }
                is ApiResult.Error -> {
                    sessionStorage.clear()
                    _uiState.value = SplashUiState(
                        isLoading = false
                    )
                }
            }

        }
    }
}