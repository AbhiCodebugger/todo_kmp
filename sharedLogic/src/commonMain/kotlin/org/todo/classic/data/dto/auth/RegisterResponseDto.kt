package org.todo.classic.data.dto.auth

import kotlinx.serialization.Serializable


@Serializable
data class RegisterResponseDto(
    val data: UserResponseDto,
    val success: Boolean
)
