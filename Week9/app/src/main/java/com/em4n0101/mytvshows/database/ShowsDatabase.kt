package com.em4n0101.mytvshows.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.response.ScheduleResponse

@Database(entities = [Show::class, Person::class, ScheduleResponse::class], version = 3, exportSchema = false)
@TypeConverters(ShowConverters::class, PersonConverters::class, ScheduleResponseConverters::class)
abstract class ShowsDatabase: RoomDatabase() {

    abstract val showsDatabaseDao: ShowsDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ShowsDatabase? = null

        fun getInstance(context: Context): ShowsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShowsDatabase::class.java,
                        "shows_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}