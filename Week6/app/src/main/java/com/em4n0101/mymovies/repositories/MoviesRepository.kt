package com.em4n0101.mymovies.repositories

import android.app.Application
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.database.MoviesDatabase
import com.em4n0101.mymovies.database.MoviesDatabaseDao
import kotlinx.coroutines.flow.Flow

class MoviesRepository(application: Application) {
    private val moviesDao: MoviesDatabaseDao = MoviesDatabase.getInstance(application).moviesDatabaseDao

    suspend fun insertMovies(movies: List<Movie>) = moviesDao.insertMovies(movies)

    fun getMovies(): Flow<List<Movie>> = moviesDao.getAllMovies()

    fun getMovieBy(title: String): Flow<Movie?> = moviesDao.getMovieBy(title)
}