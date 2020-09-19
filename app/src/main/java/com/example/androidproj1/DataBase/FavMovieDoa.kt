package com.example.androidproj1.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidproj1.Models.UI.UIMovie

@Dao
interface FavMovieDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavMovie(movie: List<UIMovie>)

    @Query("SELECT * FROM Fav_Movie_Table")
    fun getFavMovie(): List<UIMovie>

    @Query("DELETE FROM Fav_Movie_Table")
    fun deleteAllFavMovies()

}