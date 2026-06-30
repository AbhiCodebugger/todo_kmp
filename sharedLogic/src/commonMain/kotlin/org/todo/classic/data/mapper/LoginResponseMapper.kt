package org.todo.classic.data.mapper

import org.todo.classic.data.dto.auth.LoginResponseDto
import org.todo.classic.domain.model.Session

fun LoginResponseDto.toDomain(): Session {
    return Session(
        accessToken = data.token,
        refreshToken = data.refreshToken
    )
}
