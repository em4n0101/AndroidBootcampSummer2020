package com.em4n0101.mytvshows.database

import androidx.room.*
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.Show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


@Dao
interface ShowsDatabaseDao {
    /**
     * Operations for shows
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: Show)

    @Query("SELECT * FROM shows WHERE name = :name")
    fun getShowBy(name: String): Flow<Show?>

    @ExperimentalCoroutinesApi
    fun getShowByTitleDistinctUntilChanged(title: String) =
        getShowBy(title).distinctUntilChanged()

    @Query("SELECT * FROM shows")
    fun getAllShows(): Flow<List<Show>>

    @Query("DELETE FROM shows WHERE name = :name")
    suspend fun deleteShowBy(name: String)

    /**
     * Operations for persons
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    @Query("SELECT * FROM persons WHERE name = :name")
    fun getPersonBy(name: String): Flow<Person?>

    @ExperimentalCoroutinesApi
    fun getPersonByNameDistinctUntilChanged(name: String) =
        getPersonBy(name).distinctUntilChanged()

    @Query("SELECT * FROM persons")
    fun getAllPersons(): Flow<List<Person>>

    @Query("DELETE FROM persons WHERE name = :name")
    suspend fun deletePersonBy(name: String)

}