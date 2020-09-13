package com.example.androidproj1.network

import com.google.gson.annotations.SerializedName

data class TopRatedResponse(@SerializedName("results")
                            val topRated : List<Movie>)