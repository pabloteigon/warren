package com.pabloteigon.warren.domain.usecases

import com.pabloteigon.warren.domain.entities.AccessToken
import com.pabloteigon.warren.domain.repository.AccessTokenRepository
import io.reactivex.Scheduler
import io.reactivex.Single

interface GetAccessTokenUseCase {
    fun execute(body: Map<String, String>): Single<AccessToken>

    fun getAccessTokenCache(): Single<AccessToken>
}

class GetAccessTokenUseCaseImpl(
    private val accessTokenRepository: AccessTokenRepository,
    private val scheduler: Scheduler
) : GetAccessTokenUseCase {

    override fun execute(body: Map<String, String>): Single<AccessToken> {
        return accessTokenRepository.getAccessToken(body)
            .subscribeOn(scheduler)
    }

    override fun getAccessTokenCache(): Single<AccessToken> {
        return accessTokenRepository.getAccessTokenCached()
            .subscribeOn(scheduler)
    }
}