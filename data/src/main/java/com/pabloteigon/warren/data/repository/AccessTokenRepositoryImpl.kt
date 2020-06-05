package com.pabloteigon.warren.data.repository

import com.pabloteigon.warren.data.local.source.AccessTokenCacheDataSource
import com.pabloteigon.warren.data.remote.source.RemoteDataSource
import com.pabloteigon.warren.domain.entities.AccessToken
import com.pabloteigon.warren.domain.repository.AccessTokenRepository
import io.reactivex.Single

class AccessTokenRepositoryImpl(
    private val accessTokenCacheDataSource: AccessTokenCacheDataSource,
    private val remoteDataSource: RemoteDataSource
) : AccessTokenRepository {

    override fun getAccessToken(body: Map<String, String>): Single<AccessToken> {
        return getAccessTokenRemote(body)
    }

    override fun getAccessTokenCached(): Single<AccessToken> {
        return accessTokenCacheDataSource.get()
    }

    private fun getAccessTokenRemote(body: Map<String, String>): Single<AccessToken> {
        val email = body["email"]

        return remoteDataSource.getAccessToken(body)
            .flatMap { result ->
                result.email = email!!
                accessTokenCacheDataSource.insert(result)
                Single.just(result)
            }
    }

}