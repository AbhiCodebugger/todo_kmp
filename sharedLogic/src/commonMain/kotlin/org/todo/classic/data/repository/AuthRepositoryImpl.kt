package org.todo.classic.data.repository

import org.todo.classic.domain.auth.AuthManager
import org.todo.classic.data.dto.auth.LoginRequestDto
import org.todo.classic.data.dto.auth.RegisterRequestDto
import org.todo.classic.data.remote.AuthApi
import org.todo.classic.data.mapper.toDomain
import org.todo.classic.domain.result.ApiResult
import org.todo.classic.data.network.safeApiCall
import org.todo.classic.domain.model.Session
import org.todo.classic.domain.model.User
import org.todo.classic.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val authManager: AuthManager
) : AuthRepository {

    init {
        println("AuthRepository Created")
    }
    override suspend fun login(
        email: String,
        password: String
    ): ApiResult<Session> {
        return safeApiCall {
            val response = authApi.login(LoginRequestDto(
                email = email,
                password = password
            ))
            println("Response : $response")
            val session = response.toDomain()
            authManager.saveSession(session)
            session
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): ApiResult<User>{
        return safeApiCall {
             authApi.register(RegisterRequestDto(
                name = name,
                email = email,
                password = password
            )).data.toDomain()
        }
    }

    override suspend fun getCurrentUser(): ApiResult<User> {
        return safeApiCall {
            val response =  authApi.getCurrentUser()
             response.toDomain()
        }
    }
}