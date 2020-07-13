package com.em4n0101.mytvshows.networking

import com.em4n0101.mytvshows.model.response.SearchShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET("/search/shows")
    suspend fun searchShowsForUserInput(@Query("q") inputToSearch: String): List<SearchShowsResponse>
}