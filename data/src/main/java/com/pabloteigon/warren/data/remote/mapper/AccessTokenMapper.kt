package com.pabloteigon.warren.data.remote.mapper

import com.pabloteigon.warren.data.remote.model.AccessTokenPayload
import com.pabloteigon.warren.domain.entities.AccessToken

object AccessTokenMapper {

    fun map(payload: AccessTokenPayload) = AccessToken(
        email = "",
        accessToken = payload.accessToken,
        refreshToken = payload.refreshToken
    )
}