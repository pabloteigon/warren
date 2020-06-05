package com.pabloteigon.warren.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access_token")
data class AccessTokenCache(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val email: String,
    val accessToken: String,
    val refreshToken: String
)