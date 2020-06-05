package com.pabloteigon.warren.data.local.mapper

import com.pabloteigon.warren.data.local.model.AccessTokenCache
import com.pabloteigon.warren.domain.entities.AccessToken

object AccessTokenMapper {

    fun map(cache: AccessTokenCache): AccessToken = accessTokenCache(cache)

    private fun accessTokenCache(cache: AccessTokenCache) = AccessToken(
        email = cache.email,
        accessToken = cache.accessToken,
        refreshToken = cache.refreshToken
    )

    fun mapToCache(model: AccessToken) = map(model)

    private fun map(model: AccessToken) = AccessTokenCache(
        email = model.email,
        accessToken = model.accessToken,
        refreshToken = model.refreshToken
    )
}