package com.picpay.desafio.android.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DesafioAndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DesafioAndroidApp)
            androidLogger()
            modules(listOf(networkModule, appKoinModule))
        }
    }
}