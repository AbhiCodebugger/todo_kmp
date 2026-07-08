package org.todo.classic.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.todo.classic.domain.auth.AuthManager
import org.todo.classic.domain.result.ApiResult

object HttpClientFactory {
    fun create(authManagerProvider: (() -> AuthManager)? = null): HttpClient {
        println("HTTPClient Created")
        return HttpClient {
            expectSuccess = true
            install(ContentNegotiation){
                json(Json { ignoreUnknownKeys = true })
            }

            install(DefaultRequest) {
                contentType(ContentType.Application.Json)
            }
            if (authManagerProvider != null) {
                install(Auth){
                    bearer {
                        loadTokens {
                           val session = authManagerProvider().currentSession()
                            if (session == null) {
                                null
                            }else{
                                BearerTokens(
                                    accessToken = session.accessToken,
                                    refreshToken = session.refreshToken
                                )
                            }
                        }
                        refreshTokens {
                            when(val result = authManagerProvider().refreshToken()){
                                is ApiResult.Success -> {
                                    BearerTokens(
                                        accessToken = result.data.accessToken,
                                        refreshToken = result.data.refreshToken
                                    )
                                }
                                is ApiResult.Error -> {
                                    null
                                }
                            }
                        }
                        sendWithoutRequest { request ->
                            request.url.encodedPath.startsWith("/auth")
                        }
                    }
                }
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}