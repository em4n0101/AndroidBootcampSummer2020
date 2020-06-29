package com.em4n0101.mymovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.em4n0101.mymovies.repositories.MoviesRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepository(application)
    private val movies = repository.getMovies()

    fun getMovies() = movies.value
}