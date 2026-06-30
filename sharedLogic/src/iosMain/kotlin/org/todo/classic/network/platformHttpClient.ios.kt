package org.todo.classic.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun platformHttpClient(): HttpClientEngineFactory<*> {
    return Darwin
}