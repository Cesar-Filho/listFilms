package com.example.listfilms.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.listfilms.model.Favourite
import com.example.listfilms.model.Movie

@Dao
interface MovieDao: DAO<Movie> {
    @Query(value = "Select * from movies")
    fun list() : List<Movie>

    @Query(value = "Select * from movies where title = :title")
    fun byTitle(title: String) : Movie
}