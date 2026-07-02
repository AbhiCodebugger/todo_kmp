package org.todo.classic.data.repository

import org.todo.classic.data.network.ApiResult

interface TokenRefresher {
    suspend fun refreshToken(
        refreshToken: String
    ): ApiResult<String>
}