package org.todo.classic.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun platformHttpClient(): HttpClientEngineFactory<*> {
    return OkHttp
}