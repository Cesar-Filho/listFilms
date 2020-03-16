package com.example.listfilms.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.listfilms.model.User

@Dao
interface UserDao: DAO<User> {
    @Query(value = "Select * from users")
    fun list() : List<User>

    @Query(value = "Select * from users where email = :email")
    fun byEmail(email: String) : User
}