package com.em4n0101.mytvshows.app

import com.em4n0101.mytvshows.model.database.ShowsDatabase
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
import com.em4n0101.mytvshows.networking.RemoteApi
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val modelModule = module {
    single { ShowsDatabase.getDatabase(androidApplication()) }

    single { get<ShowsDatabase>().showsDatabaseDao }

    single { RemoteApi(get()) }

    single { ShowsRepository(get(), get()) }
}