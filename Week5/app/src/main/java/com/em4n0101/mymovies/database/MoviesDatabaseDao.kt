package com.em4n0101.mymovies.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.em4n0101.mymovies.data.Movie

interface MoviesDatabaseDao {
    @Insert
    fun insert(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Query("SELECT * FROM movies WHERE title = :title")
    fun get(title: String): Movie?

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movies")
    fun clean()
}