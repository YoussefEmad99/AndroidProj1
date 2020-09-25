package com.example.androidproj1.DataBase

import androidx.room.*
import com.example.androidproj1.Models.UI.FavMovieUI
import com.example.androidproj1.Models.UI.UIMovie

@Dao
interface FavMovieDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavMovie(movie: UIMovie)

    @Query("SELECT * FROM Movie_Table")
    fun getFavMovie(): List<UIMovie>

    @Query("DELETE FROM Movie_Table")
    fun deleteAllFavMovies()

    @Query("DELETE FROM Movie_Table WHERE id = :id")
    fun deleteFavMovieById(id: Int)

    @Delete
    fun deleteFavMovie(fav_movie: UIMovie)

    @Update
    fun update (fav_movie: UIMovie)

}