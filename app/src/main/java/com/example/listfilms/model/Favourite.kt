package com.example.listfilms.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favourites")
data class Favourite(
    @NotNull
    @PrimaryKey
    @SerializedName("email")
    val email: String,

    @NotNull
    @SerializedName("movieId")
    val movieId: Int
)