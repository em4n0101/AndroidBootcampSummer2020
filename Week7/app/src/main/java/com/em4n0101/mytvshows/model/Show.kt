package com.em4n0101.mytvshows.model

import android.os.Parcelable
import com.em4n0101.mytvshows.model.response.InnerImages
import com.em4n0101.mytvshows.model.response.Rating
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Show(
    val id: Int,
    val name: String,
    val genres: Array<String>?,
    val summary: String?,
    val premiered: String?,
    val rating: Rating?,
    val image: InnerImages?
) : Parcelable