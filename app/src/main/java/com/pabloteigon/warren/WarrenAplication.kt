package com.pabloteigon.warren

import android.app.Application
import com.pabloteigon.warren.data.di.dataModules
import com.pabloteigon.warren.di.presentationModule
import com.pabloteigon.warren.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WarrenAplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WarrenAplication)

            modules(domainModule + dataModules + listOf(presentationModule))
        }
    }
}