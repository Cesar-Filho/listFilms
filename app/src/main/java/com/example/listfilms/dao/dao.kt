package com.example.listfilms.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface DAO<T>
{
    @Insert
    fun save(entity: T)

    @Delete
    fun remove(entity: T)

    @Update
    fun update(entity: T)
}