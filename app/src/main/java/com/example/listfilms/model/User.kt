package com.example.listfilms.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "users")
data class User(
    @NotNull
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,

    @NotNull
    @SerializedName("email")
    val email: String
)