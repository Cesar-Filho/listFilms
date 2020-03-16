package com.example.listfilms.dao

import androidx.room.*
import com.example.listfilms.model.Favourite

@Dao
interface FavouriteDao {
    @Query(value = "Select * from favourites")
    fun list() : List<Favourite>

    @Query(value = "Select * from favourites where email = :email and movieId = :movieId")
    fun byFavourite(email: String, movieId: String) : Favourite? = null

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(favourite: Favourite)

    @Delete
    fun remove(favourite: Favourite)

    @Update
    fun update(favourite: Favourite)
}