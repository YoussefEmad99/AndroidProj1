package com.example.androidproj1.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface APIInterface {

    @GET("movie/popular?")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ) : Call<APIResponse>

    @GET ("movie/top_rated?")
    fun getTopRated(
        @Query("api_key") apiKey:String,
        @Query("page") page: Int
    ):Call<APIResponse>
}