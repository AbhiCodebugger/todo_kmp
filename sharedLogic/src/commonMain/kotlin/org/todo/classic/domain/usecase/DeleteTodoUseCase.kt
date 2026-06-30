package org.todo.classic.domain.usecase

import org.todo.classic.domain.repository.TodoRepository

class DeleteTodoUseCase (private val todoRepository: TodoRepository) {
    suspend operator fun invoke(id: Int){
        return todoRepository.deleteTodo(id)
    }
}