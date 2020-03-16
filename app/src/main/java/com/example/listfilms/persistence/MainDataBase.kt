package com.example.listfilms.persistence

import android.content.Context
import androidx.room.Room

object MainDataBase {
    private var appDatabase: AppDatabase? = null
    fun getInstance(context: Context?): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder<AppDatabase>(
                context!!,
                AppDatabase::class.java, "movies"
            ).build()
        }
        return appDatabase
    }
}