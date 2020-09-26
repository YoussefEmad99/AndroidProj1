package com.example.androidproj1.DataBase

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidproj1.Models.UI.TopRatedMovieUI

interface TopRatedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopularMovie(movie: TopRatedMovieUI)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopularListMovie(movies: List<TopRatedMovieUI>)

    @Query("SELECT * FROM top_rated")
    fun getMovie(): List<TopRatedMovieUI>

    @Query("SELECT * FROM top_rated WHERE movieId = :id")
    fun getMovieById(id: Int)

    @Query("DELETE FROM top_rated")
    fun deleteAllMovies()

    @Query("DELETE FROM top_rated WHERE movieId = :id")
    fun deleteMovieById(id: Int)

    @Delete
    fun deleteMovie(movie: TopRatedMovieUI)
}