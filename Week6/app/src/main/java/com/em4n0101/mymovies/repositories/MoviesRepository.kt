package com.em4n0101.mymovies.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.database.MoviesDatabase
import com.em4n0101.mymovies.database.MoviesDatabaseDao

class MoviesRepository(application: Application) {
    private val moviesDao: MoviesDatabaseDao = MoviesDatabase.getInstance(application).moviesDatabaseDao

    suspend fun insertMovies(movies: List<Movie>) = moviesDao.insertMovies(movies)

    fun getMovies(): LiveData<List<Movie>> {
        return moviesDao.getAllMovies()
    }

    fun getMovieBy(title: String): LiveData<Movie> {
        return moviesDao.get(title)
    }
}