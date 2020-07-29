package com.em4n0101.mytvshows.viewmodel.searchshow

import androidx.lifecycle.*
import com.em4n0101.mytvshows.model.Failure
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
import kotlinx.coroutines.launch

class SearchShowViewModel(private val repository: ShowsRepository): ViewModel() {
    private val _showsFoundedLiveData = MutableLiveData<List<Show>>()
    val showsFoundedLiveData: LiveData<List<Show>> get() = _showsFoundedLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?> get() = _errorLiveData

    fun searchForAShow(inputToSearch: String) {
        _loadingLiveData.postValue(true)

        viewModelScope.launch {
            val getShowsResponse = repository.getSearchForAShow(inputToSearch)
            _loadingLiveData.postValue(false)

            if (getShowsResponse is Success) {
                val shows = mutableListOf<Show>()
                getShowsResponse.data.forEach {
                    shows.add(it.show)
                }
                _showsFoundedLiveData.postValue(shows)
            } else {
                val failure = getShowsResponse as Failure
                _errorLiveData.postValue(failure.error)
            }
        }
    }
}