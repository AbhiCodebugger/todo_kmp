package org.todo.classic

import org.todo.classic.data.remote.AuthApi
import org.todo.classic.data.repository.AuthRepositoryImpl
import org.todo.classic.network.HttpClientFactory

object TestRunner {

//    suspend fun testLogin(){
//        val client = HttpClientFactory.create()
//        val authApi = AuthApi(client)
//        val repository = AuthRepositoryImpl(authApi)
//
//        val result = repository.login(
//            email = "abhinav@test.com",
//            password = "admin123"
//        )
//        println("Token = ${result.accessToken}")
//        println("Refresh Token = ${result.refreshToken}")
//    }
}