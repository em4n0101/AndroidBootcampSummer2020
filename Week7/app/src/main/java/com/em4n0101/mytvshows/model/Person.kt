package com.em4n0101.mytvshows.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.em4n0101.mytvshows.model.response.InnerImages
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "persons")
@Parcelize
@Serializable
data class Person(
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: Country?,
    val birthday: String?,
    val image: InnerImages?
): Parcelable