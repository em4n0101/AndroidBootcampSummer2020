package com.em4n0101.mymovies

import android.app.Application
import android.content.Context

class MyMoviesApplication: Application() {
    companion object {
        private lateinit var instance: MyMoviesApplication

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}