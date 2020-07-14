package com.em4n0101.mytvshows.repositories

import android.app.Application
import com.em4n0101.mytvshows.database.ShowsDatabase
import com.em4n0101.mytvshows.database.ShowsDatabaseDao
import com.em4n0101.mytvshows.model.Person
import kotlinx.coroutines.flow.Flow

class PersonsRepository(application: Application) {
    private val databaseDao: ShowsDatabaseDao = ShowsDatabase.getInstance(application).showsDatabaseDao

    suspend fun insertPerson(person: Person) = databaseDao.insertPerson(person)

    fun getPersons(): Flow<List<Person>> = databaseDao.getAllPersons()

    fun getPersonByName(name: String): Flow<Person?> = databaseDao.getPersonBy(name)

    suspend fun deletePersonBy(name: String) = databaseDao.deletePersonBy(name)
}