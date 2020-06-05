package com.pabloteigon.warren.data.remote.api

import com.pabloteigon.warren.data.remote.model.AccessTokenPayload
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {

    @POST("/api/v2/account/login")
    fun getAccessToken(
        @Body body: Map<String, String>
    ): Single<AccessTokenPayload>

}