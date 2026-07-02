package org.todo.classic.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation


@Composable
fun AuthPasswordField(
    value: String,
    error: String?,
    onValueChange: (String)-> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = " Password")
        },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth(),
        isError = error != null
    )
    error?.let {
        Text(
            text = it,
            color = MaterialTheme.colorScheme.error
            )
    }
}