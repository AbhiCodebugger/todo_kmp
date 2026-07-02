package org.todo.classic.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthButton(
    text: String,
    loadingText: String,
    isLoading: Boolean,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = !isLoading
    ) {
        Text(
            if(isLoading) loadingText else text
        )
    }
}