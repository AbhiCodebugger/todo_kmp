package org.todo.classic.data.repository

import org.todo.classic.data.dto.auth.LoginRequestDto
import org.todo.classic.data.dto.auth.UserResponseDto
import org.todo.classic.data.remote.AuthApi
import org.todo.classic.data.mapper.toDomain
import org.todo.classic.data.network.ApiResult
import org.todo.classic.data.network.safeApiCall
import org.todo.classic.domain.model.Session
import org.todo.classic.domain.model.User
import org.todo.classic.domain.repository.AuthRepository
import org.todo.classic.session.SessionStorage

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val sessionStorage: SessionStorage
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
            sessionStorage.save(session)
            session
        }
    }

    override suspend fun register(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): ApiResult<User> {
        return safeApiCall {
            val response =  authApi.getCurrentUser()
             response.toDomain()
        }
    }
}