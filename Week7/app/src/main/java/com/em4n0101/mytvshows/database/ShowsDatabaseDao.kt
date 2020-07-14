package com.em4n0101.mytvshows.database

import androidx.room.*
import com.em4n0101.mytvshows.model.Show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


@Dao
interface ShowsDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: Show)

    @Query("SELECT * FROM shows WHERE name = :name")
    fun getShowBy(name: String): Flow<Show?>

    @ExperimentalCoroutinesApi
    fun getShowByTitleDistinctUntilChanged(title: String) =
        getShowBy(title).distinctUntilChanged()

    @Query("SELECT * FROM shows")
    fun getAllShows(): Flow<List<Show>>
}