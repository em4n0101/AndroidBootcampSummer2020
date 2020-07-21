package com.em4n0101.mytvshows.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.em4n0101.mytvshows.model.Show
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "schedule")
data class ScheduleResponse(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val airtime: String?,
    val show: Show?
)