package com.em4n0101.mymovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.repositories.MoviesRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepository(application)
    val movies = repository.getMovies()

    fun saveMovie(movie: Movie) = repository.insert(movie)
}