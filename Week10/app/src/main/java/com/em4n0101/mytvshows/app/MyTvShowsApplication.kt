package com.em4n0101.mytvshows.app

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyTvShowsApplication: Application() {
    companion object {
        private lateinit var instance: MyTvShowsApplication
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@MyTvShowsApplication)
            modules(listOf(networkModule, modelModule, viewModelsModule))
        }
    }
}