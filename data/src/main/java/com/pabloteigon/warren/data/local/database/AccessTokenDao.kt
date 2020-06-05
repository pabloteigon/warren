package com.pabloteigon.warren.data.local.database

import androidx.room.*
import com.pabloteigon.warren.data.local.model.AccessTokenCache
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AccessTokenDao {

    @Query("SELECT * FROM access_token")
    fun get(): Single<AccessTokenCache>

    @Update
    fun update(accessToken: AccessTokenCache): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(accessToken: AccessTokenCache)

    @Query("DELETE FROM access_token")
    fun deleteAll(): Completable
}