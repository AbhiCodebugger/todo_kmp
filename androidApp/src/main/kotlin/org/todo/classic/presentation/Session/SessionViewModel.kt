package org.todo.classic.presentation.Session

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.todo.classic.domain.model.User

class SessionViewModel :  ViewModel() {
    private val _state = MutableStateFlow(SessionState())
    val state : StateFlow<SessionState> = _state.asStateFlow()

    fun onLoginSuccess (user: User){
        _state.value = SessionState(
            isLoading = false,
            isLoggedIn = true,
            user = user
        )
    }
    fun logout(){
        _state.value = SessionState(
            isLoading = false,
            isLoggedIn = false,
            user = null
        )
    }

    fun finishLoading(){
        _state.value = _state.value.copy(
            isLoading = false
        )
    }
}