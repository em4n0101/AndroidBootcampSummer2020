package com.em4n0101.mytvshows.viewmodel.searchshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.em4n0101.mytvshows.model.repositories.ShowsRepository

class SearchShowViewModelFactory(private val repository: ShowsRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ShowsRepository::class.java).newInstance(repository)
    }
}