package org.todo.classic.domain.usecase

import org.todo.classic.domain.model.Todo
import org.todo.classic.domain.repository.TodoRepository

class UpdateTodoUseCase(private val todoRepository: TodoRepository) {
    suspend operator fun invoke(todo: Todo){
        return todoRepository.updateTodo(todo)
    }
}