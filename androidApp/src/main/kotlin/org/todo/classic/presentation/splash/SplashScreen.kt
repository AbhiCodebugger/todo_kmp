package org.todo.classic.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.todo.classic.presentation.Session.SessionViewModel
import org.todo.classic.presentation.navigation.Screen


@Composable
fun SplashScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel,
    viewModel: SplashViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isLoading) {
        if(!uiState.isLoading){
          uiState.user?.let { user ->
              sessionViewModel.onLoginSuccess(user)
              navController.navigate(Screen.Dashboard.route) {
                  popUpTo(Screen.Splash.route) {
                      inclusive = true
                  }
              }
          } ?: run {
              navController.navigate(Screen.Login.route) {
                  popUpTo(Screen.Splash.route) {
                      inclusive = true
                  }
              }
          }
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