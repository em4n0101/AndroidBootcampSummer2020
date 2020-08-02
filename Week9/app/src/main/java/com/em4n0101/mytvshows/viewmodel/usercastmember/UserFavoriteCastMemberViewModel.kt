package com.em4n0101.mytvshows.viewmodel.usercastmember

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.em4n0101.mytvshows.model.repositories.ShowsRepository

class UserFavoriteCastMemberViewModel(private val repository: ShowsRepository): ViewModel() {
    fun getPersons() = repository.getPersons().asLiveData()
}