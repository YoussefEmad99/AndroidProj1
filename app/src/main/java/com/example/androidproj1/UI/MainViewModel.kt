package com.example.androidproj1.UI

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.repository.MovieRepository


class MainViewModel(application: Application) : AndroidViewModel(application),
    MovieRepository.MovieCallback {
    private val cm =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var movieData: List<UIMovie>? = null
    private var totalPages: Int = 1
    private var currentPage = 1

    private val _movieLiveData: MutableLiveData<List<UIMovie>>
            by lazy { MutableLiveData<List<UIMovie>>() }

    val movieLiveData: LiveData<List<UIMovie>>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData<String>() }
    val onError: LiveData<String>
        get() = _onError

    init {
        MovieRepository.createDatabase(application)
    }

    fun loadMovie(loadFun: (MovieRepository.MovieCallback, Int) -> Unit) {
        if (movieData != null && !isConnected()) {
            onMovieLoadingError("Error requesting further movie data")
            return
        }

        if (currentPage <= totalPages) {
            if (currentPage == 1)
                movieData = null
            loadFun(this, currentPage)
        } else
            onMovieLoadingError("Error requesting further movie data from the network")

    }

    /**
     * resets current page counter to 1 to reload from the beginning, this will incrementally delete previous movie data
     * so it can be reloaded.
     * used for when the reload button is clicked
     */
    fun resetEntries(){
        currentPage = 1
    }

    override fun onMovieReady(movies: List<UIMovie>, totalPageNum: Int) {
        //set number of total pages
        totalPages = totalPageNum
        //increment current page to request next page if there is an internet connection
        if (isConnected())
            currentPage += 1

        movieData = if (movieData != null)
            movieData!! + movies    //since movies are now loaded dynamically we cant replace them with the next page's results we need to add to them
        else
            movies

        _movieLiveData.value = movieData!!
    }

    override fun onMovieLoadingError(errorMsg: String) {
        _onError.value = errorMsg
    }

    private fun isConnected(): Boolean {
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}
