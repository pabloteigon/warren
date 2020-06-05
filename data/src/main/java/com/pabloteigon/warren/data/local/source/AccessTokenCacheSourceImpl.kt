package com.pabloteigon.warren.data.local.source

import com.pabloteigon.warren.data.local.database.AccessTokenDao
import com.pabloteigon.warren.data.local.mapper.AccessTokenMapper
import com.pabloteigon.warren.domain.entities.AccessToken
import io.reactivex.Completable
import io.reactivex.Single

class AccessTokenCacheSourceImpl(private val accessTokenDao: AccessTokenDao) :
    AccessTokenCacheDataSource {

    override fun get(): Single<AccessToken> {
        return accessTokenDao.get().map { AccessTokenMapper.map(it) }
    }

    override fun getAll(): Single<List<AccessToken>> {
        TODO("Not yet implemented")
    }

    override fun insert(data: AccessToken) {
        accessTokenDao.insert(AccessTokenMapper.mapToCache(data))
    }

    override fun update(data: AccessToken): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteAll(): Completable {
        return accessTokenDao.deleteAll()
    }
}