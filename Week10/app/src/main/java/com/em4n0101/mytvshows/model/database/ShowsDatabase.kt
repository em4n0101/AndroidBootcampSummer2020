package com.em4n0101.mytvshows.model.database

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

        fun getDatabase(
            context: Context
        ): ShowsDatabase {
            val dataBaseName = "shows_database"

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowsDatabase::class.java,
                    dataBaseName
                )
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}