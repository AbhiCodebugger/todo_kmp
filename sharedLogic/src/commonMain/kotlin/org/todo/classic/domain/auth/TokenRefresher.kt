package org.todo.classic.domain.auth

import org.todo.classic.domain.result.ApiResult

interface TokenRefresher {
    suspend fun refreshToken(
        refreshToken: String
    ): ApiResult<String>
}