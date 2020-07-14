package com.em4n0101.mytvshows.repositories

import android.app.Application
import com.em4n0101.mytvshows.database.ShowsDatabase
import com.em4n0101.mytvshows.database.ShowsDatabaseDao
import com.em4n0101.mytvshows.model.Show
import kotlinx.coroutines.flow.Flow

class ShowsRepository(application: Application) {
    private val showsDao: ShowsDatabaseDao = ShowsDatabase.getInstance(application).showsDatabaseDao

    suspend fun insertShow(show: Show) = showsDao.insertShow(show)

    fun getShows(): Flow<List<Show>> = showsDao.getAllShows()

    fun getShowBy(name: String): Flow<Show?> = showsDao.getShowBy(name)
}