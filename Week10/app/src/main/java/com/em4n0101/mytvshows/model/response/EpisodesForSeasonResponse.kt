package com.em4n0101.mytvshows.model.response

import com.em4n0101.mytvshows.model.InnerImages
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesForSeasonResponse(
    val id: Int,
    val name: String?,
    val airdate: String?,
    val image: InnerImages?,
    val summary: String?
)