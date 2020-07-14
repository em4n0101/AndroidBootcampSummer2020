package com.em4n0101.mytvshows.networking

import com.em4n0101.mytvshows.model.response.CastForShowResponse
import com.em4n0101.mytvshows.model.response.SearchShowsResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApiService {
    @GET("/search/shows")
    suspend fun searchShowsForUserInput(@Query("q") inputToSearch: String): List<SearchShowsResponse>

    @GET("/shows/{showId}/seasons")
    suspend fun getSeasonsOfShow(@Path("showId") showId: String): List<SeasonsForShowResponse>

    @GET("/shows/{showId}/cast")
    suspend fun getCastOfShow(@Path("showId") showId: String): List<CastForShowResponse>
}