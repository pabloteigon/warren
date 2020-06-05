package com.pabloteigon.warren.data.remote.source

import com.pabloteigon.warren.domain.entities.AccessToken
import io.reactivex.Single

interface RemoteDataSource {

    fun getAccessToken(body: Map<String, String>): Single<AccessToken>
}