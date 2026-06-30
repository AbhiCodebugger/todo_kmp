package org.todo.classic.data.repository

import org.todo.classic.domain.model.Todo
import org.todo.classic.domain.repository.TodoRepository

class TodoRepositoryImpl : TodoRepository {
    override suspend fun getTodos(): List<Todo> {
        TODO("Not yet implemented")
    }

    override suspend fun createTodo(title: String, description: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTodo(todo: Todo) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTodo(id: Int) {
        TODO("Not yet implemented")
    }
}