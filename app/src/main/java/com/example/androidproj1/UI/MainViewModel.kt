package com.example.androidproj1.UI

import android.app.Application
import android.os.Parcelable
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

//    private val _recyclerState : MutableLiveData<Parcelable>
//            by lazy { MutableLiveData<Parcelable>() }
//
//    val recyclerState: LiveData<Parcelable>
//        get() = _recyclerState

    init {
        MovieRepository.createDatabase(application)
    }

    //Check this one
    fun loadMovie(pageNum: Int = 1){
        MovieRepository.requestMovieData(this, pageNum)
    }

    override fun onMovieReady(movies: List<UIMovie>) {
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
