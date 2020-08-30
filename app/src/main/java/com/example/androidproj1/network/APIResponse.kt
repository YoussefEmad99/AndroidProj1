package com.example.androidproj1.network

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("original_title")
    val movieName: String,

    val popularity: Double,

    val backdropPath: String

)
