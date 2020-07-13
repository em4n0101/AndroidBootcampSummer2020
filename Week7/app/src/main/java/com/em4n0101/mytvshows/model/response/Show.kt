package com.em4n0101.mytvshows.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Show(
    val id: Int,
    val url: String,
    val name: String,
    val genres: Array<String>?,
    val runtime: Int?,
    val officialSite: String?,
    val summary: String?,
    val image: InnerImages?
)