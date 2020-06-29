package com.em4n0101.mymovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.em4n0101.mymovies.utils.Utilities

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    fun getListOfMovies() = Utilities.createMovies(context)
}