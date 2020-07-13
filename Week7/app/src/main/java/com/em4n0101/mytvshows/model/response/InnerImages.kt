package com.em4n0101.mytvshows.model.response

import kotlinx.serialization.Serializable

@Serializable
data class InnerImages(
    val medium: String,
    val original: String
)