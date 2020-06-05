package com.pabloteigon.warren.data.local.source

import io.reactivex.Completable
import io.reactivex.Single

interface CacheDataSource<T> {

    fun get(): Single<T>
    fun getAll(): Single<List<T>>
    fun insert(data: T)
    fun update(data: T): Completable
    fun deleteAll(): Completable
}

