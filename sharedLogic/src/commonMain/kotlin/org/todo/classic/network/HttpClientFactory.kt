package org.todo.classic.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.todo.classic.session.SessionStorage

object HttpClientFactory {
    fun create(sessionStorage: SessionStorage): HttpClient {
        println("HTTPClient Created")
        return HttpClient {
            expectSuccess = true
            install(ContentNegotiation){
                json(Json { ignoreUnknownKeys = true })
            }
            install(DefaultRequest){
                val session = sessionStorage.get()
                if(!session?.accessToken.isNullOrBlank() || !session?.refreshToken.isNullOrBlank()){
                    header("Authorization", "Bearer ${session.accessToken}")
                }
                contentType(ContentType.Application.Json)
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}