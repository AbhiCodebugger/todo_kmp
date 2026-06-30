package org.todo.classic.session

import org.todo.classic.domain.model.Session

interface SessionStorage {
    fun save(session: Session)
    fun get(): Session?
    fun clear()
}