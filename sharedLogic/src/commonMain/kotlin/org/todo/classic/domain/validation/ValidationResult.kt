package org.todo.classic.domain.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)