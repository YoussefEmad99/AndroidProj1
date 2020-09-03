package com.example.androidproj1.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = null

    fun getClient(): Retrofit {
        if (instance == null)
            instance = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient().newBuilder().build())
                .build()

        return instance!!
    }
}