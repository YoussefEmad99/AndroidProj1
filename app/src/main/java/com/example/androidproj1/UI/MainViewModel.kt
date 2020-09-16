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

class MainViewModel(application: Application) : AndroidViewModel(application), MovieRepository.MovieCallback {
    private val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

    private lateinit var movieData: List<UIMovie>
    private var totalPages: Int = 1

    private val _movieLiveData : MutableLiveData<List<UIMovie>>
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

    private fun isConnected(activeNetwork: NetworkInfo?): Boolean = activeNetwork?.isConnectedOrConnecting == true

    //Check this one
    fun loadMovie(pageNum: Int = 1){
        if(this::movieData.isInitialized && !isConnected(activeNetwork)){
            onMovieLoadingError("Error requesting further movie data")
            return
        }
        if(pageNum <= totalPages)
            MovieRepository.requestMovieData(this, pageNum)

    }

    override fun onMovieReady(movies: List<UIMovie>, totalPageNum: Int) {
        //set number of total pages
        totalPages = totalPageNum

        movieData = if(this::movieData.isInitialized)
            movieData + movies    //since movies are now loaded dynamically we cant replace them with the next page's results we need to add to them
        else
            movies
        _movieLiveData.value = movieData

    }

    override fun onMovieLoadingError(errorMsg: String) {
        _onError.value = errorMsg
    }

}
