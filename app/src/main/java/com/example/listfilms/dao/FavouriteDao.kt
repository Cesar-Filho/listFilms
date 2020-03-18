package com.example.listfilms.dao

import androidx.room.*
import com.example.listfilms.model.Favourite

@Dao
interface FavouriteDao : DAO<Favourite> {
    @Query(value = "Select * from favourites")
    fun list() : List<Favourite>

    @Query(value = "Select * from favourites where upper(email) = upper(:email) and movieId = :movieId")
    fun byFavourite(email: String, movieId: Int) : Favourite
}