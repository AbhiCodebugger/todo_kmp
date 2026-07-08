package org.todo.classic.presentation.session

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.todo.classic.domain.usecase.LogoutUseCase

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.todo.classic.domain.model.User

class SessionViewModel(
    private val presenter: SessionPresenter
) : ViewModel() {
    val state = presenter.state
    fun onLoginSuccess(user: User) {
        presenter.onLoginSuccess(user)
    }
    fun logout(){
        viewModelScope.launch {
            presenter.logout()
        }
    }
}