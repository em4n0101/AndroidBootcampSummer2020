package com.em4n0101.mytvshows.model

import com.em4n0101.mytvshows.model.response.InnerImages
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: Int,
    val name: String,
    val country: Country?,
    val birthday: String?,
    val image: InnerImages?
)