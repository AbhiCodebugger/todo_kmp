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
import org.todo.classic.domain.result.ApiResult


class LoginViewModel(
    private val presenter: LoginPresenter
    ) : ViewModel(){

    val state = presenter.state
    fun onEvent(event: LoginEvent){
            presenter.onEvent(event)
    }
    override fun onCleared(){
        presenter.clear()
        super.onCleared()
    }
}