package com.pabloteigon.warren.data.remote.source

import com.pabloteigon.warren.data.remote.api.ServerApi
import com.pabloteigon.warren.data.remote.mapper.AccessTokenMapper
import com.pabloteigon.warren.domain.entities.AccessToken
import io.reactivex.Single

class RemoteDataSourceImpl(private val serverApi: ServerApi) : RemoteDataSource {

    override fun getAccessToken(body: Map<String, String>): Single<AccessToken> {
        return serverApi.getAccessToken(body)
            .map { AccessTokenMapper.map(it) }
    }

}