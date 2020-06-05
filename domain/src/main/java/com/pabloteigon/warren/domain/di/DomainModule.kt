package com.pabloteigon.warren.domain.di

import com.pabloteigon.warren.domain.usecases.GetAccessTokenUseCase
import com.pabloteigon.warren.domain.usecases.GetAccessTokenUseCaseImpl
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetAccessTokenUseCase> {
        GetAccessTokenUseCaseImpl(
            accessTokenRepository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModule = listOf(useCaseModule)