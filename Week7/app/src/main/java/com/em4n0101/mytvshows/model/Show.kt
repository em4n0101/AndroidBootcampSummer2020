package com.em4n0101.mytvshows.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.em4n0101.mytvshows.model.response.InnerImages
import com.em4n0101.mytvshows.model.response.Rating
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "shows")
@Parcelize
@Serializable
data class Show(
    @PrimaryKey
    val id: Int,
    val name: String,
    val genres: Array<String>?,
    val summary: String?,
    val premiered: String?,
    val rating: Rating?,
    val image: InnerImages?
) : Parcelable