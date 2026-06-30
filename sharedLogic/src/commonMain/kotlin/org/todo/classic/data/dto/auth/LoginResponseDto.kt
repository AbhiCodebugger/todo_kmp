package org.todo.classic.data.dto.auth

import kotlinx.serialization.Serializable
import org.todo.classic.data.dto.auth.LoginDataDto

@Serializable
data class LoginResponseDto(
    val data: LoginDataDto,
    val success: Boolean
)