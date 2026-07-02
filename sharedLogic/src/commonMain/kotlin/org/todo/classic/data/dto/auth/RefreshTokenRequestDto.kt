package org.todo.classic.data.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RefreshTokenRequestDto(
    @SerialName("refresh_token")
    val refreshToken: String
)
