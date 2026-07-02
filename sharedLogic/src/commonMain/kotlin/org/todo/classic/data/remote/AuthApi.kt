package org.todo.classic.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.todo.classic.data.dto.auth.LoginRequestDto
import org.todo.classic.data.dto.auth.LoginResponseDto
import org.todo.classic.data.dto.auth.RefreshTokenRequestDto
import org.todo.classic.data.dto.auth.RefreshTokenResponseDto
import org.todo.classic.data.dto.auth.RegisterRequestDto
import org.todo.classic.data.dto.auth.RegisterResponseDto
import org.todo.classic.data.dto.auth.UserResponseDto
import org.todo.classic.data.network.ApiResult
import org.todo.classic.network.ApiConfig

class AuthApi (private val client: HttpClient) {

    init {
        println("AuthApi Created")
    }
    suspend fun login(request: LoginRequestDto): LoginResponseDto {
        return client.post("${ApiConfig.BASE_URL}/auth/login") {
        contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun register(request: RegisterRequestDto): RegisterResponseDto {
        return client.post("${ApiConfig.BASE_URL}/auth/register"){
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
    suspend fun getCurrentUser(): UserResponseDto {
        return client.get("${ApiConfig.BASE_URL}/auth/me").body()
    }
    suspend fun refreshToken(req: RefreshTokenRequestDto): RefreshTokenResponseDto {
        return client.post("${ApiConfig.BASE_URL}/auth/refresh"){
            contentType(ContentType.Application.Json)
            setBody(req)
        }.body()
    }
}