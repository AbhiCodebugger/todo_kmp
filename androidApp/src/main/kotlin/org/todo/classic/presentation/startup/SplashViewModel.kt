package org.todo.classic.presentation.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SplashViewModel(
   private val presenter: StartupPresenter
) : ViewModel() {
    val state = presenter.state

    fun onAppStarted() {
        viewModelScope.launch {
            presenter.onAppStarted()
        }
    }
}