package org.todo.classic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext
import org.todo.classic.di.androidModule
import org.todo.classic.di.initKoin
import org.todo.classic.presentation.login.LoginScreen
import org.todo.classic.presentation.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initKoin {
            androidContext(this@MainActivity)
            modules(androidModule)
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