package com.example.listfilms.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listfilms.dao.FavouriteDao
import com.example.listfilms.dao.MovieDao
import com.example.listfilms.dao.UserDao
import com.example.listfilms.model.Favourite
import com.example.listfilms.model.Movie
import com.example.listfilms.model.User

@Database(entities = [User::class, Favourite::class, Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieFavouriteDao(): FavouriteDao
    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
}