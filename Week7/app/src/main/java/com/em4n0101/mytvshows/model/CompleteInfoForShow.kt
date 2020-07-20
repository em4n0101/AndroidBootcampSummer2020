package com.em4n0101.mytvshows.model

import com.em4n0101.mytvshows.model.response.CastForShowResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import kotlinx.serialization.Serializable

@Serializable
class CompleteInfoForShow (
    val seasons: List<SeasonsForShowResponse>,
    val cast: List<CastForShowResponse>
)