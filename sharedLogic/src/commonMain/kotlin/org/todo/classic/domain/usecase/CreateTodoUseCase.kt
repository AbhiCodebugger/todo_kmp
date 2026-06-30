package org.todo.classic.domain.usecase

import org.todo.classic.domain.repository.TodoRepository

class CreateTodoUseCase(private val todoRepository: TodoRepository) {

    suspend operator fun invoke(title: String, description: String){
        return todoRepository.createTodo(title, description)
    }
}