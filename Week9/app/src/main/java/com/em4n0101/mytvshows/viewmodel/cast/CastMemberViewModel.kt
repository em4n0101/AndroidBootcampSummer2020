package com.em4n0101.mytvshows.viewmodel.cast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CastMemberViewModel(private val repository: ShowsRepository): ViewModel() {
    fun savePerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPerson(person)
    }

    fun getPersonByName(name: String) = repository.getPersonByName(name).asLiveData()

    fun deletePersonByName(name: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deletePersonBy(name)
    }
}