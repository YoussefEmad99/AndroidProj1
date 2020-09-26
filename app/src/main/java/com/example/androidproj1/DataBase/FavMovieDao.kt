package com.example.androidproj1.DataBase

import androidx.room.*
import com.example.androidproj1.Models.UI.FavMovieUI
import com.example.androidproj1.Models.UI.UIMovie

@Dao
interface FavMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavMovie(movie: FavMovieUI)

    @Query("SELECT * FROM Fav_Movie_Table WHERE movieId = :id")
    fun getFavMovieById(id: Int): FavMovieUI?

    @Query("SELECT * FROM Fav_Movie_Table")
    fun getFavMovies(): List<FavMovieUI>

    @Query("DELETE FROM Fav_Movie_Table")
    fun deleteAllFavMovies()

    @Query("DELETE FROM Fav_Movie_Table WHERE movieId = :id")
    fun deleteFavMovieById(id: Int)

    @Delete
    fun deleteFavMovie(fav_movie: FavMovieUI)
}