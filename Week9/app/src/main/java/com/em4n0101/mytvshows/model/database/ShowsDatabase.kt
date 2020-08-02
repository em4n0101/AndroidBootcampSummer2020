package com.em4n0101.mytvshows.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.response.ScheduleResponse

@Database(entities = [Show::class, Person::class, ScheduleResponse::class], version = 3, exportSchema = false)
@TypeConverters(ShowConverters::class, PersonConverters::class, ScheduleResponseConverters::class)
abstract class ShowsDatabase: RoomDatabase() {
    abstract val showsDatabaseDao: ShowsDatabaseDao
}