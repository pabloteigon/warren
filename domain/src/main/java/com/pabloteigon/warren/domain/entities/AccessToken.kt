package com.pabloteigon.warren.domain.entities

data class AccessToken(
    var email: String,
    val accessToken: String,
    val refreshToken: String
)