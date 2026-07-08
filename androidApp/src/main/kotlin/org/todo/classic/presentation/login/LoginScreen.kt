package org.todo.classic.presentation.login
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.todo.classic.presentation.navigation.Screen



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.todo.classic.presentation.session.SessionViewModel
import org.todo.classic.presentation.components.AuthButton
import org.todo.classic.presentation.components.AuthPasswordField
import org.todo.classic.presentation.components.AuthTextField
import org.todo.classic.presentation.navigation.NavigationResultKeys


@Composable
fun LoginScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel,
    viewModel: LoginViewModel = koinViewModel()) {
    val uiState by viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val registerSuccess = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow(NavigationResultKeys.REGISTER_SUCCESS, false)
        ?.collectAsState()

    LaunchedEffect(registerSuccess?.value) {
        if(registerSuccess?.value == true){
            snackbarHostState.showSnackbar(
                message = "Registration successful. Please login."
            )
            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.set("REGISTER_SUCCESS", false)
        }
    }

    LaunchedEffect(uiState.user) {
        uiState.user?.let { user ->
            sessionViewModel.onLoginSuccess(user)
            navController.navigate(Screen.Dashboard.route){
                popUpTo(Screen.Login.route) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            Arrangement.Center) {

            Text(text = "Login Screen", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(32.dp))

            AuthTextField(
                value = uiState.email,
                onValueChange = {
                  viewModel.onEvent(LoginEvent.EmailChanged(it))
                },
                label = "Email",
                error = uiState.emailError
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthPasswordField(
            value = uiState.password,
            onValueChange = {
                viewModel.onEvent(LoginEvent.PasswordChanged(it))
            },
            error = uiState.passwordError
        )
        Spacer(modifier = Modifier.height(26.dp))
        if (uiState.error != null){
            Text(text = uiState.error!!, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (uiState.isLoading){
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
        }
        AuthButton(
            text = "Login",
            loadingText = "Logging in...",
            isLoading = uiState.isLoading,
            onClick = {
                viewModel.onEvent(LoginEvent.LoginClicked)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                navController.navigate(Screen.Register.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Don't have an account? Register")
        }
    }
}
}