package com.em4n0101.mytvshows.database

import androidx.room.TypeConverter
import com.em4n0101.mytvshows.model.InnerImages
import com.em4n0101.mytvshows.model.Network
import com.em4n0101.mytvshows.model.Rating

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
            return value.medium + "," + value.original
        }

        private const val emptyImageUrl =
            "https://lh3.googleusercontent.com/proxy/NsdqXQzwKh_V4kvqtVw9DDoG8pbOOJoGAPv8UQUS-rJD0a-0tB6ypPgy3hoat3G4aMNp9mcC6uQZLoFu6-dcHhQlZBGaepWBGis_gRzJMmzg7PDrYHN8OGmP-xVdSucc"

        @TypeConverter
        @JvmStatic
        fun toImage(value: String): InnerImages {
            val images = value.split(",")
            var mediumImage = emptyImageUrl
            var originalImage = emptyImageUrl
            if (images.size == 2) {
                mediumImage = images[0]
                originalImage = images[1]
            }
            return InnerImages(
                mediumImage,
                originalImage
            )
        }

        @TypeConverter
        @JvmStatic
        fun fromNetwork(value: Network): String {
            return value.name ?: ""
        }

        @TypeConverter
        @JvmStatic
        fun toNetwork(value: String): Network {
            return Network(value)
        }
    }
}