package org.todo.classic.domain.usecase

import org.todo.classic.domain.model.Todo
import org.todo.classic.domain.repository.TodoRepository

class GetTodosUseCase(private val todoRepository: TodoRepository) {
    suspend operator fun invoke(): List<Todo> {
        return todoRepository.getTodos()
    }
}