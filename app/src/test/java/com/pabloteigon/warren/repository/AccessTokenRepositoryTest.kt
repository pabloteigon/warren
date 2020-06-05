package com.pabloteigon.warren.repository

import android.app.Application
import com.pabloteigon.warren.base.BaseTest
import com.pabloteigon.warren.data.di.dataModules
import com.pabloteigon.warren.domain.entities.AccessToken
import com.pabloteigon.warren.domain.repository.AccessTokenRepository
import com.pabloteigon.warren.feature.viewmodel.StateMachineSingle
import com.pabloteigon.warren.feature.viewmodel.ViewState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AccessTokenRepositoryTest : BaseTest() {

    //Target
    private val mRepo: AccessTokenRepository by inject()

    @Mock
    private lateinit var context: Application

    private val mThrowableMessage = "Bad Request"

    @Before
    fun start() {
        super.setUp()

        startKoin {
            androidContext(context)
            modules(dataModules)
        }
    }

    @Test
    fun test_login_retrieves_expected_data() = runBlocking {

        var accessToken: AccessToken? = null
        var error = false
        var throwable = Throwable()

        val dataReceived = mRepo.getAccessToken(getBody())
            .compose(StateMachineSingle())
            .subscribe(
                {
                    if (it is ViewState.Success) {
                        accessToken = it.data

                    } else if (it is ViewState.Failed) {
                        error = true
                        throwable = it.throwable
                    }
                },
                {

                }
            )
        assertNotNull(dataReceived)
        assertNotNull(accessToken)
    }

    @Test
    fun test_login_retrieves_expected_error() = runBlocking {
        var error = false
        var throwable = Throwable()

        val dataReceived = mRepo.getAccessToken(getBodyWithPasswordError())
            .compose(StateMachineSingle())
            .subscribe(
                {
                    if (it is ViewState.Success) {
                        error = false

                    } else if (it is ViewState.Failed) {
                        error = true
                        throwable = it.throwable
                    }
                },
                {
                    error = true
                    throwable = it
                }
            )
        assertNotNull(dataReceived)
        assert(error)
        assertEquals(mThrowableMessage, throwable.message)
    }

    private fun getBody(): HashMap<String, String> {
        val body = HashMap<String, String>()

        body["email"] = "mobile_test@warrenbrasil.com"
        body["password"] = "Warren123!"

        return body
    }

    private fun getBodyWithPasswordError(): HashMap<String, String> {
        val body = HashMap<String, String>()

        body["email"] = "mobile_test@warrenbrasil.com"
        body["password"] = "Warren"

        return body
    }
}