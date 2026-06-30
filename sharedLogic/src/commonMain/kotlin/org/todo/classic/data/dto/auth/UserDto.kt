package org.todo.classic.data.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserResponseDto(
    @SerialName("ID")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("email")
    val email: String,

    @SerialName("Role")
    val role: String,
)