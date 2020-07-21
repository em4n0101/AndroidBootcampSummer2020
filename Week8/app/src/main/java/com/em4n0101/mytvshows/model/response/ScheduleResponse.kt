package com.em4n0101.mytvshows.model.response

import com.em4n0101.mytvshows.model.Show
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    val id: Int,
    val name: String?,
    val airtime: String?,
    val show: Show?
)