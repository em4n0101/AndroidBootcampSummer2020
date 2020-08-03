package com.em4n0101.mytvshows.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.em4n0101.mytvshows.model.database.ShowsDatabase
import com.em4n0101.mytvshows.networking.RemoteApi
import com.em4n0101.mytvshows.networking.buildApiServices
import com.em4n0101.mytvshows.model.repositories.ShowsRepository

class MyTvShowsApplication: Application() {
    companion object {
        private lateinit var instance: MyTvShowsApplication

        fun getAppContext(): Context = instance.applicationContext

        private val apiService by lazy { buildApiServices() }

        val remoteApi by lazy { RemoteApi(apiService) }

        val database: ShowsDatabase by lazy {
            val dataBaseName = "shows_database"
            Room.databaseBuilder(
                getAppContext(),
                ShowsDatabase::class.java,
                dataBaseName
            ).fallbackToDestructiveMigrationOnDowngrade()
                .fallbackToDestructiveMigration()
                .build()
        }

        val showsRepository: ShowsRepository by lazy {
            val dao = database.showsDatabaseDao
            ShowsRepository(dao, remoteApi)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}