package com.em4n0101.mytvshows.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.repositories.ShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShowsRepository(application)

    fun saveShow(show: Show) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertShow(show)
    }

    fun getShows() = repository.getShows().asLiveData()

    fun getSchedule() = repository.getSchedule().asLiveData()

    fun getShowByName(name: String) = repository.getShowBy(name).asLiveData()

    fun deleteShowByName(name: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteShowBy(name)
    }
}