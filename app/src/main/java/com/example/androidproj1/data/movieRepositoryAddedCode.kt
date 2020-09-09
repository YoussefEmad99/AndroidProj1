package com.example.androidproj1.data
import androidx.lifecycle.LiveData

class movieRepositoryAddedCode(private val movieDao: movieDao) {

    val readAllData: LiveData<List<movie>> = movieDao.readAllData()

    suspend fun addMovie(movie: movie){
        movieDao.addmovie(movie)
    }
}