package org.todo.classic.domain.model

data class Todo(
    val id: Long,
    val title: String,
    val description: String,
    val completed: Boolean,
    val favourite:  Boolean,
    val createdBy: String,
    val userId: Long
)