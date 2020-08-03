package com.em4n0101.mytvshows.networking

import com.em4n0101.mytvshows.model.CompleteInfoForShow
import com.em4n0101.mytvshows.model.Failure
import com.em4n0101.mytvshows.model.Result
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.model.response.EpisodesForSeasonResponse
import com.em4n0101.mytvshows.model.response.ScheduleResponse
import com.em4n0101.mytvshows.model.response.SearchShowsResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse

class RemoteApi(private val remoteApiService: RemoteApiService) {

    suspend fun searchForAShow(inputToSearch: String): Result<List<SearchShowsResponse>> = try {
        val data = remoteApiService.searchShowsForUserInput(inputToSearch)
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }

    private suspend fun getSeasonsForShow(showId: String): Result<List<SeasonsForShowResponse>> = try {
        val data = remoteApiService.getSeasonsOfShow(showId)
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }

    suspend fun getCompleteInfoForShow(showId: String): Result<CompleteInfoForShow> = try {
        val seasonsForShow = getSeasonsForShow(showId)

        if (seasonsForShow is Success) {
            val castForShow = remoteApiService.getCastOfShow(showId)
            Success(CompleteInfoForShow(seasonsForShow.data, castForShow))
        } else {
            Failure(NullPointerException("Not info for show"))
        }
    } catch (error: Throwable) {
        Failure(error)
    }

    suspend fun getEpisodesForSeason(seasonId: String): Result<List<EpisodesForSeasonResponse>> = try {
        val data = remoteApiService.getEpisodesForSeason(seasonId)
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }

    suspend fun getCurrentSchedule(): Result<List<ScheduleResponse>> = try {
        val data = remoteApiService.getCurrentSchedule()
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }
}