package com.example.androidproj1.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproj1.network.APIResponse
import com.example.androidproj1.repository.MovieRepository

class MainViewModel :ViewModel(), MovieRepository.MovieCallback {

    private lateinit var movieData: APIResponse


    private val _movieLiveData : MutableLiveData<APIResponse>
            by lazy { MutableLiveData<APIResponse>() }
    val movieLiveData: LiveData<APIResponse>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData<String>() }
    val onError: LiveData<String>
        get() = _onError

    fun loadMovie(isForcedReload: Boolean = false){
        if (this::movieData.isInitialized && !isForcedReload ) {
            _movieLiveData.value= movieData
            return
        }
        MovieRepository.requestMovieData(this)

    }

    override fun onMovieReady(movies: APIResponse) {
        movieData = movies
        _movieLiveData.value = movieData

    }

    override fun onMovieLoadingError(errorMsg: String) {
        _onError.value = errorMsg

    }

}
