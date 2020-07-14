package com.em4n0101.mytvshows.networking

import com.em4n0101.mytvshows.model.Failure
import com.em4n0101.mytvshows.model.Result
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.model.response.SearchShowsResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse

class RemoteApi(private val remoteApiService: RemoteApiService) {

    suspend fun searchForAShow(inputToSearch: String): Result<List<SearchShowsResponse>> = try {
        val data = remoteApiService.searchShowsForUserInput(inputToSearch)
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }

    suspend fun getSeasonsForShow(showId: String): Result<List<SeasonsForShowResponse>> = try {
        val data = remoteApiService.getSeasonsOfShow(showId)
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }
}