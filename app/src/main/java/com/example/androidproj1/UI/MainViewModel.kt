package com.example.androidproj1.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.repository.MovieRepository

class MainViewModel(application: Application) : AndroidViewModel(application), MovieRepository.MovieCallback {

    private lateinit var movieData: List<UIMovie>

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

    //Check this one
    fun loadMovie(isForcedReload: Boolean = false){
        if (this::movieData.isInitialized && !isForcedReload ) {
            _movieLiveData.value = movieData
            return
        }

        MovieRepository.requestMovieData(this)
    }

    override fun onMovieReady(movies: List<UIMovie>) {
        movieData = movies
        _movieLiveData.value = movieData

    }

    override fun onMovieLoadingError(errorMsg: String) {
        _onError.value = errorMsg
    }

}
