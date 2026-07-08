package org.todo.classic.presentation.dashboard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.todo.classic.presentation.session.SessionViewModel
import org.todo.classic.presentation.navigation.Screen
import org.todo.classic.presentation.startup.AuthenticationState


@Composable
fun DashboardScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel
    ) {

    val sessionState by sessionViewModel.state.collectAsState()

    val user = sessionState.user

    LaunchedEffect(sessionState.authenticationState) {
        if (sessionState.authenticationState != AuthenticationState.Authenticated) {
            navController.navigate(Screen.Login.route) {
                popUpTo(0) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            if (user != null) {
                Text(text = "Welcome ${user.name}",
                    style = MaterialTheme.typography.headlineMedium
                    )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Logged in as ${user.role}")
            }else{
                Text(text = "No user logged in.")
            }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            sessionViewModel.logout()
        }) {
            Text(text = "Logout")
        }
    }
}