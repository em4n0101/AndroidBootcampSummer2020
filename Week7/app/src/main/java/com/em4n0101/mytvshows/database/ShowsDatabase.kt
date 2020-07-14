package com.em4n0101.mytvshows.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.em4n0101.mytvshows.model.Show

@Database(entities = [Show::class], version = 1, exportSchema = false)
@TypeConverters(ShowConverters::class)
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
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}