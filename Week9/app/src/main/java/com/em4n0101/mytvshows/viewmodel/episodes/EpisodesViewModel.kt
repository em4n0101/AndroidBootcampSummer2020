package com.em4n0101.mytvshows.viewmodel.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.em4n0101.mytvshows.model.Failure
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.model.response.EpisodesForSeasonResponse
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
import kotlinx.coroutines.launch

class EpisodesViewModel(private val repository: ShowsRepository): ViewModel() {
    private val _episodesForSeasonLiveData = MutableLiveData<List<EpisodesForSeasonResponse>>()
    val episodesForSeasonLiveData: LiveData<List<EpisodesForSeasonResponse>> get() = _episodesForSeasonLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?> get() = _errorLiveData

    fun getEpisodesForSeason(seasonId: String) {
        _loadingLiveData.postValue(true)

        viewModelScope.launch {
            val getEpisodesResponse = repository.getEpisodesForSeason(seasonId)
            _loadingLiveData.postValue(false)

            if (getEpisodesResponse is Success) {
                _episodesForSeasonLiveData.postValue(getEpisodesResponse.data)
            } else {
                val failure = getEpisodesResponse as Failure
                _errorLiveData.postValue(failure.error)
            }
        }
    }
}