package com.example.androidproj1.network

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val movies: List<Movie>
)

data class Movie(
    @SerializedName("original_title")
    val movieName: String,

    @SerializedName("vote_average")
    val popularity: Double,

    @SerializedName("poster_path")
    val imageURL: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("release_date")
    val date: String,

    @SerializedName("id")
    val id: Int
)
