package com.example.androidproj1.Models.UI

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie_Table")
data class Movie (@PrimaryKey(autoGenerate = true) val id:Int,
                  val title:String,
                  val popularity:Double,
                  val discription:String)