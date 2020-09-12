package com.example.androidproj1.repository

import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.network.APIInterface
import com.example.androidproj1.network.APIResponse
import com.example.androidproj1.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieRepository {

    private val apiServices: APIInterface by lazy {
        RetrofitClient.getClient().create(APIInterface::class.java)
    }

    private const val apiKey = "9011f3fdc6551ebe547f181c79680b66"

    fun requestMovieData(callback: MovieCallback) {
        apiServices.getMovies(apiKey)
            .enqueue(object : Callback<APIResponse> {
                override fun onResponse(
                    call: Call<APIResponse>, response: Response<APIResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = MovieMapper.mapToMovieList(response.body()!!)
                        callback.onMovieReady(movieList)
                    } else if (response.code() in 400..404) {
                        val msg = "The Movie that you are looking for is not found"
                        callback.onMovieLoadingError(msg)
                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data"
                    callback.onMovieLoadingError(msg)
                }
            })
    }

    interface MovieCallback {
        fun onMovieReady(movies: List<UIMovie>)
        fun onMovieLoadingError(errorMsg: String)
    }

}