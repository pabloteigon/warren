package com.pabloteigon.warren.data.di

import com.pabloteigon.warren.data.BuildConfig
import com.pabloteigon.warren.data.interceptor.HttpExceptionInterceptor
import com.pabloteigon.warren.data.remote.api.ServerApi
import com.pabloteigon.warren.data.remote.source.RemoteDataSource
import com.pabloteigon.warren.data.remote.source.RemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory {
        providesOkHttpClient()
    }

    single {
        createWebService<ServerApi>(okHttpClient = get(), url = BuildConfig.API_URL)
    }

    factory<RemoteDataSource> {
        RemoteDataSourceImpl(serverApi = get())
    }
}

fun providesOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    val exceptionInterceptor = HttpExceptionInterceptor()

    logging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor(exceptionInterceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}