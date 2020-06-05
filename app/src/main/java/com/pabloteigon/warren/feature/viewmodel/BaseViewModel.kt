package com.pabloteigon.warren.feature.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val errorState = MutableLiveData<ViewState<Nothing>>().apply {}
    val successState = MutableLiveData<ViewState<Nothing>>().apply {}
    val loadingVisibility = MutableLiveData<Boolean>().apply { value = false }
    val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}