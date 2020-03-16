package com.example.listfilms.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.listfilms.model.Favourite

@Dao
interface FavouriteDao: DAO<Favourite> {
    @Query(value = "Select * from favourites")
    fun list() : List<Favourite>

    @Query(value = "Select * from favourites where email = :email")
    fun byUserId(email: String) : Favourite
}