package org.todo.classic.data.auth

import org.todo.classic.data.dto.auth.RefreshTokenRequestDto
import org.todo.classic.data.network.safeApiCall
import org.todo.classic.data.remote.AuthApi
import org.todo.classic.domain.auth.TokenRefresher
import org.todo.classic.domain.result.ApiResult

class TokenRefresherImpl(
    private val authApi: AuthApi
): TokenRefresher {
    override suspend fun refreshToken(refreshToken: String): ApiResult<String> {
        return safeApiCall {
            val response = authApi.refreshToken(
                RefreshTokenRequestDto(
                    refreshToken = refreshToken
                )
            )
            response.data.accessToken
        }
    }


}