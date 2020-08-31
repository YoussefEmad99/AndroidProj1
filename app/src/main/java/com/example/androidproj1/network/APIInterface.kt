package com.example.androidproj1.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface APIInterface {

    @GET("movie/popular?")
    fun getMovies(
        @Query("api_key") apiKey: String
    ) : Call<APIResponse>

    @GET("movie/{movie_id}/images?")
    fun getMovieImage(
        @Path("movie_id") movie_id: String,
        @Query("api_key") apiKey: String
    ) : Call<APIImageResponse>

}