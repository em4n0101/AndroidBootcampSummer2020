package com.em4n0101.mytvshows.model.response

import com.em4n0101.mytvshows.model.Person
import kotlinx.serialization.Serializable

@Serializable
data class CastForShowResponse(
    val person: Person
)