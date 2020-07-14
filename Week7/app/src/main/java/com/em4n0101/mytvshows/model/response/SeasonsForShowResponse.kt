package com.em4n0101.mytvshows.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SeasonsForShowResponse(
    val id: Int,
    val number: Int,
    val episodeOrder: Int?,
    val premiereDate: String?,
    val summary: String?,
    val image: InnerImages?
)