package org.todo.classic.presentation.login
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.todo.classic.presentation.navigation.Screen



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.todo.classic.presentation.Session.SessionViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel,
    viewModel: LoginViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

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

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),Arrangement.Center) {

        Text(text = "Login Screen", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = {
               viewModel.onEmailChanged(it)
            },
            label = {
                Text("Email")
            },
            isError = uiState.emailError != null,
            modifier = Modifier.fillMaxWidth()
        )
        uiState.emailError?.let {
            Text(text = it,
                color = MaterialTheme.colorScheme.error
                )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = uiState.password,
            onValueChange = {
               viewModel.onPasswordChanged(it)
            },
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.passError != null,
            modifier = Modifier.fillMaxWidth()
        )
        uiState.passError?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(26.dp))
        if (uiState.error != null){
            Text(text = uiState.error!!, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (uiState.isLoading){
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = {viewModel.login()},
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
            ) {
            Text(
                if (uiState.isLoading)
                "Logging in..."
               else
                "Login"
            )
        }

    }
}