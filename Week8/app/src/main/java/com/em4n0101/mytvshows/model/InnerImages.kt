package com.em4n0101.mytvshows.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class InnerImages(
    val medium: String,
    val original: String
): Parcelable