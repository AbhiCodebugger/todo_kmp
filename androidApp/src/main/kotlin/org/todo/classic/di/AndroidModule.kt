package org.todo.classic.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.todo.classic.presentation.session.SessionViewModel
import org.todo.classic.presentation.login.LoginViewModel
import org.todo.classic.presentation.register.RegisterViewModel
import org.todo.classic.session.SessionStorage
import org.todo.classic.session.SessionStorageImpl

val androidModule = module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences("todo_preferences", Context.MODE_PRIVATE)
    }

    single<SessionStorage> {
        SessionStorageImpl(get())
    }

    viewModel {
        SessionViewModel(get())
    }

    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }

}