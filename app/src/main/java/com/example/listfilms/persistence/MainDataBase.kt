package com.example.listfilms.persistence

import android.content.Context
import androidx.room.Room

object MainDataBase {
    private var appDatabase: AppDatabase? = null
    fun getInstance(context: Context): AppDatabase {
        if (appDatabase == null) {
            appDatabase = context.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java, "list-movies"
                ).allowMainThreadQueries().build()
            }
        }
        return this.appDatabase!!
    }
}