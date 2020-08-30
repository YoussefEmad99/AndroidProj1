package com.example.androidproj1.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface APIInterface {

    @GET("/movie/550?")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("original_title") movieTitle: String,
        @Query ("popularity") popularity: Double,
        @Query("backdropPath") backdropPath: String
    ) : Call<APIResponse>


}