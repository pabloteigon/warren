package com.pabloteigon.warren.data.di

import com.pabloteigon.warren.data.repository.AccessTokenRepositoryImpl
import com.pabloteigon.warren.domain.repository.AccessTokenRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<AccessTokenRepository> {
        AccessTokenRepositoryImpl(
            accessTokenCacheDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)