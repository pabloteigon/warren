package com.pabloteigon.warren.data.remote.model

data class AccessTokenPayload(
    val accessToken: String,
    val refreshToken: String
)