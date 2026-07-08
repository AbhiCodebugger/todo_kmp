package org.todo.classic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.todo.classic.di.androidModule
import org.todo.classic.di.initKoin
import org.todo.classic.domain.auth.AuthManager
import org.todo.classic.presentation.login.LoginScreen
import org.todo.classic.presentation.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    private val authManager: AuthManager by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initKoin {
            androidContext(this@MainActivity)
            modules(androidModule)
        }
        lifecycleScope.launch {
            delay(15000)
            val result = authManager.refreshToken()
            println("Refresh Result = $result")
        }

        setContent {
            MaterialTheme {
                AppNavGraph()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
   AppNavGraph()
}