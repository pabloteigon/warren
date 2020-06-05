package com.pabloteigon.warren.data.interceptor

import com.pabloteigon.warren.data.exception.AccessDeniedException
import com.pabloteigon.warren.data.exception.BadRequestException
import com.pabloteigon.warren.data.exception.ConflictException
import com.pabloteigon.warren.data.exception.UnauthorizedException
import okhttp3.Interceptor
import okhttp3.Response

class HttpExceptionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() == 400) {
            throw BadRequestException(response.message())
        }
        if (response.code() == 401) {
            throw UnauthorizedException(response.message())
        }
        if (response.code() == 403) {
            throw AccessDeniedException(response.message())
        }
        if (response.code() == 409) {
            throw ConflictException()
        }

        return response
    }
}