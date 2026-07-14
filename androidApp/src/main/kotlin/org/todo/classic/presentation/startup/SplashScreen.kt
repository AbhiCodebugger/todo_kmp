package org.todo.classic.presentation.startup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.todo.classic.presentation.session.AuthenticationState
import org.todo.classic.presentation.session.SessionViewModel


@Composable
fun SplashScreen(
    sessionViewModel: SessionViewModel,
    onNavigate : (AuthenticationState) -> Unit
) {
    val state by sessionViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        sessionViewModel.initialSession()
    }

    LaunchedEffect(state.authenticationState) {
        when(state.authenticationState) {
            AuthenticationState.Loading -> Unit
            else -> onNavigate(state.authenticationState)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}