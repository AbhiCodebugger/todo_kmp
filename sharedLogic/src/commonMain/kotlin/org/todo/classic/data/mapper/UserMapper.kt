package org.todo.classic.data.mapper

import org.todo.classic.data.dto.auth.UserResponseDto
import org.todo.classic.domain.model.User

fun UserResponseDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        role = role
    )
}