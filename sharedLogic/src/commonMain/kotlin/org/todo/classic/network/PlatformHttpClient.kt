package org.todo.classic.network

import io.ktor.client.engine.HttpClientEngineFactory

expect fun platformHttpClient(): HttpClientEngineFactory<*>