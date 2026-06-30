package org.todo.classic.di

import org.koin.dsl.module
import org.todo.classic.data.remote.AuthApi
import org.todo.classic.data.repository.AuthRepositoryImpl
import org.todo.classic.domain.repository.AuthRepository
import org.todo.classic.domain.usecase.GetCurrentUserUseCase
import org.todo.classic.domain.usecase.LoginUseCase
import org.todo.classic.domain.validation.ValidateEmailUseCase
import org.todo.classic.domain.validation.ValidatePasswordUseCase
import org.todo.classic.network.HttpClientFactory

val sharedModule = module {
    single {
        HttpClientFactory.create(get())
    }
    single {
        AuthApi(get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
    factory {
        LoginUseCase(get())
    }
    factory {
        GetCurrentUserUseCase(get())
    }

    factory {
        ValidateEmailUseCase()
    }
    factory {
        ValidatePasswordUseCase()
    }

}