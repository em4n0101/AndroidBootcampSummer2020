package com.em4n0101.mytvshows.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Rating(val average: Double?): Parcelable
