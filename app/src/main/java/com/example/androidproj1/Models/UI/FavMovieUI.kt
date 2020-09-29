package com.example.androidproj1.Models.UI

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Fav_Movie_Table", foreignKeys = [ForeignKey(
        entity = UIMovie::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("movieId"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
class FavMovieUI(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int = 0
)