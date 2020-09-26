package com.example.androidproj1.Models.UI

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "most_popular", foreignKeys = [ForeignKey(
        entity = UIMovie::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("movieId"),
        onDelete = ForeignKey.CASCADE
    )]
)
class PopularMovieUI(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int = 0
)
