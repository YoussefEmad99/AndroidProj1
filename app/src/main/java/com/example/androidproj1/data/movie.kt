package com.example.androidproj1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL
@Entity(tableName = "movie_table")
data class movie (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val vote_average: Float,
    val poster_path: URL

    )