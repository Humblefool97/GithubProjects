package com.example.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.dao.CachedProjectDao
import com.example.cache.dao.ConfigDao
import com.example.cache.model.CachedProjectEntity
import com.example.cache.model.Config
import javax.inject.Inject

@Database(entities = [CachedProjectEntity::class, Config::class], version = 1)
abstract class AppDatabase @Inject constructor() : RoomDatabase() {
    abstract fun provideProjectDao(): CachedProjectDao
    abstract fun provideConfigDao(): ConfigDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE == Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            "AppDb.db"
                        ).build()
                    }
                    return INSTANCE as AppDatabase
                }
            }

            return INSTANCE as AppDatabase
        }
    }
}