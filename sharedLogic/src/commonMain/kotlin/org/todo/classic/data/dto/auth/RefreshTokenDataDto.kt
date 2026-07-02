package org.todo.classic.data.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RefreshTokenDataDto(

    @SerialName("access_token")
    val accessToken: String
)
