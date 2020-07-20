package com.em4n0101.mytvshows.database

import androidx.room.TypeConverter
import com.em4n0101.mytvshows.model.Country

class PersonConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromCountry(value: Country): String {
            return value.name ?: ""
        }

        @TypeConverter
        @JvmStatic
        fun toCountry(value: String): Country {
            return Country(value)
        }
    }
}
