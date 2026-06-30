package org.todo.classic.domain.repository

import org.todo.classic.domain.model.Todo

interface TodoRepository {
    suspend fun getTodos(): List<Todo>
    suspend fun createTodo(title: String, description: String)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(id: Int)
}