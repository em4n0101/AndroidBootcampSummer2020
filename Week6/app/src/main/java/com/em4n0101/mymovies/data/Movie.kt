package com.em4n0101.mymovies.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    val title: String,
    val posterResource: Int,
    val genre: String,
    val releaseDate: String,
    val duration: String,
    val summary: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var isFavorite: Boolean? = false)