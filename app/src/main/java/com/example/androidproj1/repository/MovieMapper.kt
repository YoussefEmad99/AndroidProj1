package com.example.androidproj1.repository

import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.network.APIResponse

object MovieMapper {

    fun mapToMovieList(response: APIResponse): List<UIMovie>{
        return response.movies.map {
            UIMovie(it.movieName, it.popularity, it.imageURL)
        }
    }
}