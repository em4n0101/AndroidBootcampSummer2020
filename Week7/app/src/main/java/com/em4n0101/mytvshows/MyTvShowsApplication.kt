package com.em4n0101.mytvshows

import android.app.Application
import android.content.Context
import com.em4n0101.mytvshows.networking.RemoteApi
import com.em4n0101.mytvshows.networking.buildApiServices

class MyTvShowsApplication: Application() {
    companion object {
        private lateinit var instance: MyTvShowsApplication

        fun getAppContext(): Context = instance.applicationContext

        private val apiService by lazy { buildApiServices() }

        val remoteApi by lazy { RemoteApi(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}