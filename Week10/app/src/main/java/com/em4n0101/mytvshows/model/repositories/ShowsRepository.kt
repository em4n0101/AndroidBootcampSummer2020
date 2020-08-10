package com.em4n0101.mytvshows.model.repositories

import com.em4n0101.mytvshows.model.database.ShowsDatabaseDao
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.response.ScheduleResponse
import com.em4n0101.mytvshows.networking.RemoteApi
import kotlinx.coroutines.flow.Flow

class ShowsRepository(private var databaseDao: ShowsDatabaseDao, private val remoteApi: RemoteApi) {

    /**
     * Show operations
     */
    suspend fun insertShow(show: Show) = databaseDao.insertShow(show)

    fun getShows(): Flow<List<Show>> = databaseDao.getAllShows()

    fun getShowBy(name: String): Flow<Show?> = databaseDao.getShowBy(name)

    suspend fun deleteShowBy(name: String) = databaseDao.deleteShowBy(name)

    fun getSchedule(): Flow<List<ScheduleResponse>> = databaseDao.getSchedule()

    suspend fun getEpisodesForSeason(seasonId: String)
            = remoteApi.getEpisodesForSeason(seasonId)

    suspend fun getSearchForAShow(inputToSearch: String)
            = remoteApi.searchForAShow(inputToSearch)

    suspend fun getCompleteInfoForShowId(showId: String) =
        remoteApi.getCompleteInfoForShow(showId)

    /**
     * Person operations
     */
    suspend fun insertPerson(person: Person) = databaseDao.insertPerson(person)

    fun getPersons(): Flow<List<Person>> = databaseDao.getAllPersons()

    fun getPersonByName(name: String): Flow<Person?> = databaseDao.getPersonBy(name)

    suspend fun deletePersonBy(name: String) = databaseDao.deletePersonBy(name)
}