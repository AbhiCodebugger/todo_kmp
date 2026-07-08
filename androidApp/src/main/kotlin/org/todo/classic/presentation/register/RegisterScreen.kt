package org.todo.classic.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.todo.classic.presentation.components.AuthButton
import org.todo.classic.presentation.components.AuthPasswordField
import org.todo.classic.presentation.components.AuthTextField
import org.todo.classic.presentation.navigation.NavigationResultKeys


@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = koinViewModel()
    ) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(uiState.isRegistered) {

        if(uiState.isRegistered){
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set(NavigationResultKeys.REGISTER_SUCCESS, true)
            navController.popBackStack()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),Arrangement.Center) {

        Text(text = "Register Screen", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(32.dp))

        AuthTextField(
            value = uiState.name,
            onValueChange = {viewModel.onEvent(RegisterEvent.NameChanged(it))},
            label = "Name",
            error = uiState.nameError
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthTextField(
            value = uiState.email,
            onValueChange = {viewModel.onEvent(RegisterEvent.EmailChanged(it))},
            label = "Email",
            error = uiState.emailError
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthPasswordField(
            value = uiState.password,
            onValueChange = {viewModel.onEvent(RegisterEvent.PasswordChanged(it))},
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
            text = "Register",
            loadingText = "Registering...",
            isLoading = uiState.isLoading,
            onClick = {viewModel.onEvent(RegisterEvent.RegisterClicked)}
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login")
        }
    }

}