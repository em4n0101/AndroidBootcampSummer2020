package com.em4n0101.mytvshows.viewmodel.showdetails

import androidx.lifecycle.*
import com.em4n0101.mytvshows.model.CompleteInfoForShow
import com.em4n0101.mytvshows.model.Failure
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowDetailsViewModel(private val repository: ShowsRepository): ViewModel() {
    fun saveShow(show: Show) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertShow(show)
    }

    fun getShowByName(name: String) = repository.getShowBy(name).asLiveData()

    fun deleteShowByName(name: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteShowBy(name)
    }

    private val _showCompleteInfoLiveData = MutableLiveData<CompleteInfoForShow>()
    val showCompleteInfoLiveData: LiveData<CompleteInfoForShow> get() = _showCompleteInfoLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?> get() = _errorLiveData

    fun getCompleteInfoForShow(show: Show) {
        _loadingLiveData.postValue(true)

        viewModelScope.launch {
            val getShowInfoResponse = repository.getCompleteInfoForShowId(show.id.toString())
            _loadingLiveData.postValue(false)

            if (getShowInfoResponse is Success) {
                _showCompleteInfoLiveData.postValue(getShowInfoResponse.data)
            } else {
                val failure = getShowInfoResponse as Failure
                _errorLiveData.postValue(failure.error)
            }
        }
    }
}