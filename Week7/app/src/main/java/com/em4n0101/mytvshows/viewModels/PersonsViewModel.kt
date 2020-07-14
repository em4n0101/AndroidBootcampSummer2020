package com.em4n0101.mytvshows.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.repositories.PersonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PersonsRepository(application)

    fun savePerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPerson(person)
    }

    fun getPersons() = repository.getPersons().asLiveData()

    fun getPersonByNameFlow(name: String) = repository.getPersonByName(name)
}