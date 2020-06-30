package com.em4n0101.mymovies.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.database.MoviesDatabase
import com.em4n0101.mymovies.database.MoviesDatabaseDao

class MoviesRepository(application: Application) {
    private val moviesDao: MoviesDatabaseDao = MoviesDatabase.getInstance(application).moviesDatabaseDao

    fun insert(movie: Movie) {
        InsertAsyncTask(moviesDao).execute(movie)
    }

    fun getMovies(): LiveData<List<Movie>> {
        return moviesDao.getAllMovies()
    }

    fun getMovieBy(title: String): LiveData<Movie> {
        return moviesDao.get(title)
    }
}

private class InsertAsyncTask(private val moviesDao: MoviesDatabaseDao):
    AsyncTask<Movie, Void, Void>() {
    override fun doInBackground(vararg params: Movie?): Void? {
        for (movie in params) {
            if (movie != null) {
                moviesDao.insert(movie)
            }
        }
        return null
    }
}

private class UpdateAsyncTask(private val moviesDao: MoviesDatabaseDao):
    AsyncTask<Movie, Void, Void>() {
    override fun doInBackground(vararg params: Movie?): Void? {
        for (movie in params) {
            if (movie != null) {
                moviesDao.update(movie)
            }
        }
        return null
    }
}

