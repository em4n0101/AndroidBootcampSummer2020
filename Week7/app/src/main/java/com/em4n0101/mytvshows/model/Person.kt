package com.em4n0101.mytvshows.model

import android.os.Parcelable
import com.em4n0101.mytvshows.model.response.InnerImages
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Person(
    val id: Int,
    val name: String,
    val country: Country?,
    val birthday: String?,
    val image: InnerImages?
): Parcelable