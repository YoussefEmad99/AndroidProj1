package com.example.androidproj1.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TopRatedInterface {

    @GET ("top_rated?")
    fun getTopRated(
        @Query("api_key") apiKey:String
    ):Call<TopRatedResponse>
}