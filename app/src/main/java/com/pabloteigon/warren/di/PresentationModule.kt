package com.pabloteigon.warren.di

import com.pabloteigon.warren.feature.login.LoginViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        LoginViewModel(
            getAccessTokenUseCase = get(),
            uiScheduler = AndroidSchedulers.mainThread()
        )
    }

}