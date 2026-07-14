package org.todo.classic.di

import org.koin.dsl.module
import org.todo.classic.data.auth.TokenRefresherImpl
import org.todo.classic.data.remote.AuthApi
import org.todo.classic.data.repository.AuthRepositoryImpl
import org.todo.classic.domain.auth.AuthManager
import org.todo.classic.domain.auth.DefaultAuthManager
import org.todo.classic.domain.auth.TokenRefresher
import org.todo.classic.domain.repository.AuthRepository
import org.todo.classic.domain.usecase.GetCurrentUserUseCase
import org.todo.classic.domain.usecase.LoginUseCase
import org.todo.classic.domain.usecase.LogoutUseCase
import org.todo.classic.domain.usecase.RegisterUseCase
import org.todo.classic.domain.validation.ValidateEmailUseCase
import org.todo.classic.domain.validation.ValidateNameUseCase
import org.todo.classic.domain.validation.ValidatePasswordUseCase
import org.todo.classic.network.HttpClientFactory
import org.todo.classic.presentation.login.LoginPresenter
import org.todo.classic.presentation.register.RegisterPresenter
import org.todo.classic.presentation.session.SessionPresenter

val sharedModule = module {
    single {
        HttpClientFactory.create(
            authManagerProvider = { get() }
        )
    }
    single {
        AuthApi(get())
    }
    single <TokenRefresher> {
        TokenRefresherImpl(
            authApi = get()
        )
    }
    single<AuthManager> {
        DefaultAuthManager(
            sessionStorage = get(),
            tokenRefresher = get()
        )
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
    single {
        SessionPresenter(get(), get())
    }
    factory {
        LoginPresenter(
            get(),
            get(),
            get(),
            get()
        )
    }
    factory {
        RegisterPresenter(
            get(),
            get(),
            get(),
            get()
        )
    }
    factory {
        LoginUseCase(get())
    }
    factory {
        RegisterUseCase(get())
    }
    factory {
        GetCurrentUserUseCase(get())
    }
    factory {
        LogoutUseCase(get(), get())
    }

    factory {
        ValidateEmailUseCase()
    }
    factory {
        ValidatePasswordUseCase()
    }
    factory {
        ValidateNameUseCase()
    }

}