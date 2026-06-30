package org.todo.classic

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform