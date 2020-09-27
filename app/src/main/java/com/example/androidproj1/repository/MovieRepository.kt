package com.example.androidproj1.repository

import android.content.Context
import android.util.Log
import com.example.androidproj1.DataBase.MovieDB
import com.example.androidproj1.Models.UI.FavMovieUI
import com.example.androidproj1.Models.UI.PopularMovieUI
import com.example.androidproj1.Models.UI.TopRatedMovieUI
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
    lateinit var appDatabase: MovieDB

    private const val apiKey = "9011f3fdc6551ebe547f181c79680b66"

    /**
     * requests popular movie data
     */
    fun requestMovieData(callback: MovieCallback, pageNum: Int) {
        apiServices.getMovies(apiKey, pageNum)
            .enqueue(object : Callback<APIResponse> {
                override fun onResponse(
                    call: Call<APIResponse>, response: Response<APIResponse>
                ) {
                    if (response.isSuccessful) {
                        //if the api response requested the first page -> Delete previous movie entries before adding newer ones
                        if(pageNum == 1)
                            appDatabase.getPopularMovieDao().deleteAllMovies()
                        //Add new movie entries to database
                        val responseBody = response.body()!!
                        val movieList = MovieMapper.mapToMovieList(responseBody)
                        //add movies to main table in database
                        appDatabase.getMovieDao().addMovie(movieList)
                        //add movie Ids to popular movies table
                        appDatabase.getPopularMovieDao().addPopularListMovie(
                            movieList.map { PopularMovieUI(it.id) })
                        callback.onMovieReady(movieList, responseBody.totalPages)
                    } else if (response.code() in 400..404) {
                        val msg = "The Movie that you are looking for is not found"
                        callback.onMovieLoadingError(msg)
                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data"
                    callback.onMovieLoadingError(msg)
                    //get list of movie ids in the popular movie database
                    val idList = appDatabase.getPopularMovieDao().getMovie().map { it.movieId }
                    //query main movie list using list of ids obtained
                    val movieList = appDatabase.getMovieDao().getMovieListById(idList)
                    // call database if available
                    if(movieList.isNotEmpty())
                        callback.onMovieReady(appDatabase.getMovieDao().getMovie())
                }
            })
    }

    fun requestTopRated(callback: MovieCallback, pageNum: Int){
        apiServices.getTopRated(apiKey, pageNum)
            .enqueue(object : Callback<APIResponse> {
                override fun onResponse(
                    call: Call<APIResponse>, response: Response<APIResponse>
                ) {
                    if (response.isSuccessful) {
                        //TODO: if the api response requested the first page -> Delete previous movie entries before adding newer ones (For TopRated movies)
                        //if the api response requested the first page -> Delete previous movie entries before adding newer ones
                        if(pageNum == 1)
                            appDatabase.getTopRatedMovieDao().deleteAllMovies()

                        //TODO: Add new movie entries to database (For TopRated movies)
                        val responseBody = response.body()!!
                        val movieList = MovieMapper.mapToMovieList(responseBody)
                        appDatabase.getMovieDao().addMovie(movieList)
                        appDatabase.getTopRatedMovieDao().addPopularListMovie(
                            movieList.map { TopRatedMovieUI(it.id) })
                        callback.onMovieReady(movieList, responseBody.totalPages)
                    } else if (response.code() in 400..404) {
                        val msg = "The Movie that you are looking for is not found"
                        callback.onMovieLoadingError(msg)
                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data"
                    callback.onMovieLoadingError(msg)

                    //get list of movie ids in the top rated movie database
                    val idList = appDatabase.getTopRatedMovieDao().getMovie().map { it.movieId }
                    //query main movie list using list of ids obtained
                    val movieList = appDatabase.getMovieDao().getMovieListById(idList)
                    if(movieList.isNotEmpty())
                        callback.onMovieReady(appDatabase.getMovieDao().getMovie())
                }
            })
    }

    fun isMovieFav(id: Int): Boolean {
        return appDatabase.getFavMovieDao().getFavMovieById(id) != null
    }

    fun getFavouriteMovieList(): List<Int>{
        return appDatabase.getFavMovieDao().getFavMovies().map { it.movieId }
    }

    fun addMovieFav(id: Int){
        appDatabase.getFavMovieDao().addFavMovie(FavMovieUI(id))
    }

    fun deleteMovieFav(id: Int){
        appDatabase.getFavMovieDao().deleteFavMovieById(id)
    }

    fun createDatabase(context: Context){
        appDatabase = MovieDB.getDatabase(context)
    }

    interface MovieCallback {
        fun onMovieReady(movies: List<UIMovie>, totalPageNum: Int = 1)
        fun onMovieLoadingError(errorMsg: String)
    }

}