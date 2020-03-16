package com.example.listfilms.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("overview")
    var overview: String
)