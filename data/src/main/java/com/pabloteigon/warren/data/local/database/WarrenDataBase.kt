package com.pabloteigon.warren.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pabloteigon.warren.data.local.model.AccessTokenCache

@Database(
    entities = [AccessTokenCache::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WarrenDataBase : RoomDatabase() {

    abstract fun accessTokenDao(): AccessTokenDao

    companion object {

        fun createDataBase(context: Context): WarrenDataBase {
            return Room
                .databaseBuilder(context, WarrenDataBase::class.java, "warren.db")
                .build()
        }
    }
}