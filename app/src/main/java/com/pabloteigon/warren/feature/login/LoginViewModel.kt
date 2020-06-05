package com.pabloteigon.warren.feature.login

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.pabloteigon.warren.Constants.HOME_FRAGMENT
import com.pabloteigon.warren.Constants.LOGIN_FRAGMENT
import com.pabloteigon.warren.domain.usecases.GetAccessTokenUseCase
import com.pabloteigon.warren.feature.RxBus
import com.pabloteigon.warren.feature.viewmodel.BaseViewModel
import com.pabloteigon.warren.feature.viewmodel.StateMachineSingle
import com.pabloteigon.warren.feature.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class LoginViewModel(
    val getAccessTokenUseCase: GetAccessTokenUseCase,
    val uiScheduler: Scheduler
) : BaseViewModel(), LifecycleObserver {

    var email = MutableLiveData<String>().apply { value = null }
    var password = MutableLiveData<String>().apply { value = null }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onStart() {
        checkLogin()
    }

    fun getAccessToken() {
        loadingVisibility.value = true
        disposables += getAccessTokenUseCase.execute(getAccessTokenBody())
            .compose(StateMachineSingle())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    if (it is ViewState.Success) {
                        Log.w("getAccessToken", "Success!")
                        startHomeFragment()

                    } else if (it is ViewState.Failed) {
                        Log.e("getAccessToken", "Error $it")
                        errorState.value = ViewState.Failed(it.throwable)
                    }
                    loadingVisibility.value = false

                },
                {
                    loadingVisibility.value = false
                    errorState.value = ViewState.Failed(it)
                }
            )
    }

    private fun checkLogin() {
        loadingVisibility.value = true
        disposables += getAccessTokenUseCase.getAccessTokenCache()
            .compose(StateMachineSingle())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    if (it is ViewState.Success) {
                        Log.w("checkLogin", "Success!")
                        email.value = it.data.email
                        startHomeFragment()
                    }
                    loadingVisibility.value = false
                },
                {
                    loadingVisibility.value = false
                    errorState.value = ViewState.Failed(it)
                }
            )
    }

    private fun getAccessTokenBody(): HashMap<String, String> {
        val body = HashMap<String, String>()
        body["email"] = "mobile_test@warrenbrasil.com"
        //body["email"] = email.value!!
        body["password"] = "Warren123!"
        //body["password"] = password.value!!
        return body
    }

    private fun startHomeFragment() {
        RxBus.publish(LOGIN_FRAGMENT, HOME_FRAGMENT)
    }
}