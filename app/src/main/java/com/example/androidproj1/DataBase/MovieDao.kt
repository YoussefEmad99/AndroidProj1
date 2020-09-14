package com.example.androidproj1.DataBase

import androidx.room.*
import com.example.androidproj1.Models.UI.UIMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: List<UIMovie>)

    @Query("SELECT * FROM Movie_Table")
    fun getMovie(): List<UIMovie>

    @Query("DELETE FROM Movie_Table")
    fun deleteAllMovies()


}
