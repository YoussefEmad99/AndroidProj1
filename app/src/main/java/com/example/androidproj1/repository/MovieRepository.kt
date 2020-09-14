package com.example.androidproj1.repository

import android.content.Context
import com.example.androidproj1.DataBase.MovieDB
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
    private lateinit var appDatabase: MovieDB

    private const val apiKey = "9011f3fdc6551ebe547f181c79680b66"

    fun requestMovieData(callback: MovieCallback) {
        apiServices.getMovies(apiKey)
            .enqueue(object : Callback<APIResponse> {
                override fun onResponse(
                    call: Call<APIResponse>, response: Response<APIResponse>
                ) {
                    if (response.isSuccessful) {
                        //Delete previous movie entries before adding newer ones
                        appDatabase.getMovieDao().deleteAllMovies()
                        //Add new movie entries to database
                        val movieList = MovieMapper.mapToMovieList(response.body()!!)
                        appDatabase.getMovieDao().addMovie(movieList)
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
                    // call database if available
                    val movieList = appDatabase.getMovieDao().getMovie()
                    if(movieList.isNotEmpty())
                        callback.onMovieReady(appDatabase.getMovieDao().getMovie())
                }
            })
    }

    fun requestTopRated(callback: MovieCallback){
        apiServices.getTopRated(apiKey)
            .enqueue(object : Callback<APIResponse> {
                override fun onResponse(
                    call: Call<APIResponse>, response: Response<APIResponse>
                ) {
                    if (response.isSuccessful) {
                        //TODO: Delete previous movie entries before adding newer ones (For TopRated movies)
                        appDatabase.getMovieDao().deleteAllMovies()

                        //TODO: Add new movie entries to database (For TopRated movies)
                        val movieList = MovieMapper.mapToMovieList(response.body()!!)
                        //appDatabase.getMovieDao().addMovie(movieList)
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

                    //TODO: all database if available (For TopRated movies)
                    val movieList = appDatabase.getMovieDao().getMovie()
                    if(movieList.isNotEmpty())
                        callback.onMovieReady(appDatabase.getMovieDao().getMovie())
                }
            })
    }
    fun createDatabase(context: Context){
        appDatabase = MovieDB.getDatabase(context)
    }

    interface MovieCallback {
        fun onMovieReady(movies: List<UIMovie>)
        fun onMovieLoadingError(errorMsg: String)
    }

}