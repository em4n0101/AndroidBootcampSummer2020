package com.em4n0101.mytvshows.model.response

import android.os.Parcelable
import com.em4n0101.mytvshows.model.InnerImages
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class SeasonsForShowResponse(
    val id: Int,
    val number: Int,
    val episodeOrder: Int?,
    val premiereDate: String?,
    val summary: String?,
    val image: InnerImages?
): Parcelable