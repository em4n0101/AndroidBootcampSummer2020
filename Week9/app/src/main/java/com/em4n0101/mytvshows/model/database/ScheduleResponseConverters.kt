package com.em4n0101.mytvshows.model.database

import androidx.room.TypeConverter
import com.em4n0101.mytvshows.model.InnerImages
import com.em4n0101.mytvshows.model.Network
import com.em4n0101.mytvshows.model.Show

class ScheduleResponseConverters {
    companion object {
        private const val emptyImageUrl =
            "https://lh3.googleusercontent.com/proxy/NsdqXQzwKh_V4kvqtVw9DDoG8pbOOJoGAPv8UQUS-rJD0a-0tB6ypPgy3hoat3G4aMNp9mcC6uQZLoFu6-dcHhQlZBGaepWBGis_gRzJMmzg7PDrYHN8OGmP-xVdSucc"


        @TypeConverter
        @JvmStatic
        fun fromShow(value: Show): String {
            var network = ""
            network = value.network?.name.toString()
            var imagePath = emptyImageUrl
            imagePath = value.image?.medium.toString()
            var summary = ""
            summary = value.summary.toString()

            return value.id.toString() + "," +
                    value.name + "," +
                    network + "," +
                    imagePath
        }

        @TypeConverter
        @JvmStatic
        fun toShow(value: String): Show {
            val items = value.split(",")
            var id = 1
            var name = ""
            var networkName = ""
            var imagePath = emptyImageUrl
            if (items.size == 4) {
                id = items[0].toInt()
                name = items[1]
                networkName = items[2]
                imagePath = items[3]
            }
            return Show(
                id,
                name,
                null,
                null,
                null,
                null,
                InnerImages(imagePath, imagePath),
                Network(networkName)
            )
        }
    }
}