package org.todo.classic.presentation.dashboard


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.todo.classic.domain.model.User
import org.todo.classic.presentation.Session.SessionViewModel



@Composable
fun DashboardScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel
    ) {

    val sessionState by sessionViewModel.state.collectAsState()

    val user = sessionState.user

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
    }
}