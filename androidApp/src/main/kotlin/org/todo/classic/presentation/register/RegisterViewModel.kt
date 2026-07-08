package org.todo.classic.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.domain.usecase.RegisterUseCase
import org.todo.classic.domain.validation.ValidateEmailUseCase
import org.todo.classic.domain.validation.ValidateNameUseCase
import org.todo.classic.domain.validation.ValidatePasswordUseCase

class RegisterViewModel(
    private val presenter: RegisterPresenter
): ViewModel() {

    val state =  presenter.state

    fun onEvent(event: RegisterEvent){
        presenter.onEvent(event)
    }

    override fun onCleared() {
        presenter.clear()
        super.onCleared()
    }
}
