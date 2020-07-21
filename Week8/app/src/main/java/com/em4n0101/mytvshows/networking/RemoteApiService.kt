package com.em4n0101.mytvshows.networking

import com.em4n0101.mytvshows.model.response.*
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

    @GET("/seasons/{seasonId}/episodes")
    suspend fun getEpisodesForSeason(@Path("seasonId") seasonId: String): List<EpisodesForSeasonResponse>

    @GET("/schedule")
    suspend fun getCurrentSchedule(): List<ScheduleResponse>
}