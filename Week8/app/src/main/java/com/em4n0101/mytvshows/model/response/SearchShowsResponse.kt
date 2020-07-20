package com.em4n0101.mytvshows.model.response

import com.em4n0101.mytvshows.model.Show
import kotlinx.serialization.Serializable

@Serializable
data class SearchShowsResponse(val score: Double, val show: Show)