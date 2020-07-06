package com.em4n0101.mymovies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.em4n0101.mymovies.data.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface MoviesDatabaseDao {
    @Insert
    suspend fun insertMovies(movies: List<Movie>)

    @Update
    fun update(movie: Movie)

    @Query("SELECT * FROM movies WHERE title = :title")
    fun getMovieBy(title: String): Flow<Movie?>

    @ExperimentalCoroutinesApi
    fun getMovieByTitleDistinctUntilChanged(title: String) =
        getMovieBy(title).distinctUntilChanged()

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("DELETE FROM movies")
    fun clean()
}