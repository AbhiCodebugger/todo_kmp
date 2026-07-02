package org.todo.classic.data.dto.auth

import kotlinx.serialization.Serializable


@Serializable
data class RefreshTokenResponseDto(
    val data: RefreshTokenDataDto,
    val success: Boolean
)
