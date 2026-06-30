package org.todo.classic.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDTO(
    val code: String? = null,
    val message: String,
    val success: Boolean
)