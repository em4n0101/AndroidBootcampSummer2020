package com.em4n0101.mytvshows.model.response

import android.os.Parcelable
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