package com.example.androidproj1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidproj1.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val apiInterface : APIInterface by lazy{
//            RetrofitClient.getClient().create(APIInterface::class.java)
//        }
//        apiInterface.getMovieImage("605116", "9011f3fdc6551ebe547f181c79680b66").
//        enqueue(object: Callback<APIImageResponse> {
//            override fun onFailure(call: Call<APIImageResponse>, t: Throwable) {
//                Log.d("MainActivity", t.cause.toString())
//            }
//
//            override fun onResponse(
//                call: Call<APIImageResponse>,
//                response: Response<APIImageResponse>
//            ) {
//                Log.d("MainActivity", response.body()?.images?.first().toString())
//            }
//
//        })
//        apiInterface.getMovies("9011f3fdc6551ebe547f181c79680b66").
//                enqueue(object : Callback<APIResponse> {
//                    override fun onFailure(call: Call<APIResponse>, t: Throwable) {
//                        Log.d("MainActivity", t.cause.toString())
//                    }
//
//                    override fun onResponse(
//                        call: Call<APIResponse>,
//                        response: Response<APIResponse>
//                    ) {
//                        Log.d("MainActivity", response.body()?.movies?.first().toString())
//                    }
//
//                })
    }
}