package com.em4n0101.mytvshows.database

import androidx.room.TypeConverter
import com.em4n0101.mytvshows.model.response.InnerImages
import com.em4n0101.mytvshows.model.response.Rating

class ShowConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromRating(value: Rating): Double {
            return value.average ?: 0.0
        }

        @TypeConverter
        @JvmStatic
        fun toRating(value: Double): Rating {
            return Rating(value)
        }

        @TypeConverter
        @JvmStatic
        fun fromGenres(value: Array<String>): String {
            return value.joinToString(separator = ", ")
        }

        @TypeConverter
        @JvmStatic
        fun toGenres(value: String): Array<String> {
            return value.split(",").toTypedArray()
        }


        @TypeConverter
        @JvmStatic
        fun fromImage(value: InnerImages): String {
            return value.medium
        }

        @TypeConverter
        @JvmStatic
        fun toImage(value: String): InnerImages {
            return InnerImages(value, value)
        }
    }
}