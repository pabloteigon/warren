package com.pabloteigon.warren.domain.repository

import com.pabloteigon.warren.domain.entities.AccessToken
import io.reactivex.Single

interface AccessTokenRepository {

    fun getAccessToken(body: Map<String, String>): Single<AccessToken>

    fun getAccessTokenCached(): Single<AccessToken>

}