package com.example.androidproj1.repository

import com.example.androidproj1.Models.UI.Movie
import com.example.androidproj1.network.APIResponse

object MovieMapper {

    fun mapToMovieList(response: APIResponse): List<Movie>{
        return response.movies.map {
            Movie(it.movieName, it.popularity, it.imageURL)
        }
    }
}