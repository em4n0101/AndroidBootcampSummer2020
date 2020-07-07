package com.em4n0101.mymovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepository(application)

    fun saveMovies(movies: List<Movie>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertMovies(movies)
    }

    fun getMoviesFlow() = repository.getMovies()

    fun getMovieByTitleFlow(title: String) = repository.getMovieBy(title)

    fun updateMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateMovie(movie)
    }

    fun deleteMovies() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteMovies()
    }
}