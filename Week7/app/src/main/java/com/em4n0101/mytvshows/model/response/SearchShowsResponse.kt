package com.em4n0101.mytvshows.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SearchShowsResponse(val score: Double, val show: Show)