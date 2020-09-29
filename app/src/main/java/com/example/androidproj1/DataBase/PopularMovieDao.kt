package com.example.androidproj1.DataBase

import androidx.room.*
import com.example.androidproj1.Models.UI.PopularMovieUI

@Dao
interface PopularMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopularMovie(movie: PopularMovieUI)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopularListMovie(movies: List<PopularMovieUI>)

    @Query("SELECT * FROM most_popular")
    fun getMovie(): List<PopularMovieUI>

    @Query("DELETE FROM most_popular")
    fun deleteAllMovies()

    @Query("DELETE FROM most_popular WHERE movieId = :id")
    fun deleteMovieById(id: Int)

    @Delete
    fun deleteMovie(movie: PopularMovieUI)
}