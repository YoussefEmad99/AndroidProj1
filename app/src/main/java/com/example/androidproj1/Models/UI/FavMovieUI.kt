package com.example.androidproj1.Models.UI

import androidx.room.Entity

@Entity(tableName = "Fav_Movie_Table")
data class FavMovieUI( val title: String,
                       val popularity: Double,
                       val imgPath: String,
                       val description: String,
                       val date: String,
                       val is_fav: Boolean,
                       @androidx.room.PrimaryKey(autoGenerate = true)
                       val id: Int = 0)