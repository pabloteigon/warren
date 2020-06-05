package com.pabloteigon.warren.data.di

import com.pabloteigon.warren.data.local.database.WarrenDataBase
import com.pabloteigon.warren.data.local.source.AccessTokenCacheDataSource
import com.pabloteigon.warren.data.local.source.AccessTokenCacheSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    single { WarrenDataBase.createDataBase(androidContext()) }

    factory<AccessTokenCacheDataSource> {
        AccessTokenCacheSourceImpl(
            accessTokenDao = get<WarrenDataBase>().accessTokenDao()
        )
    }
}