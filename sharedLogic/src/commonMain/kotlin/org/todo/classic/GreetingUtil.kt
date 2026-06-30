package org.todo.classic

import org.todo.classic.data.remote.AuthApi
import org.todo.classic.data.repository.AuthRepositoryImpl
import org.todo.classic.network.HttpClientFactory

fun sayHello(to: String): String =
    "Hello, $to!"