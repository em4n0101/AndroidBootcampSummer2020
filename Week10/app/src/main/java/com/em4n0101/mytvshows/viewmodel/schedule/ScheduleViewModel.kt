package com.em4n0101.mytvshows.viewmodel.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.em4n0101.mytvshows.model.repositories.ShowsRepository

class ScheduleViewModel(private val repository: ShowsRepository): ViewModel() {
    fun getSchedule() = repository.getSchedule().asLiveData()
}