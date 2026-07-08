package org.todo.classic.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BasePresenter {

    protected val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate
    )
    open fun clear() {
        scope.cancel()
    }
}