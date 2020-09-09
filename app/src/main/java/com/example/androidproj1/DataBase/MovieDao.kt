package com.example.androidproj1.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidproj1.Models.UI.UIMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie:UIMovie)

    @Query("SELECT * FROM Movie_Table")
    fun getMovie(): List<UIMovie>


}
