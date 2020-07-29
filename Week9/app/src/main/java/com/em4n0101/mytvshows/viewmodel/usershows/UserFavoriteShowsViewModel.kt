package com.em4n0101.mytvshows.viewmodel.usershows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.em4n0101.mytvshows.model.repositories.ShowsRepository

class UserFavoriteShowsViewModel(private val repository: ShowsRepository): ViewModel() {
    fun getShows() = repository.getShows().asLiveData()
}