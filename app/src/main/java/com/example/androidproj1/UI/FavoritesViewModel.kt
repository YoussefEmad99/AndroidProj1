package com.example.androidproj1.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.repository.MovieRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application),MovieRepository.MovieCallback {

    private val _favoritesLiveData : MutableLiveData<List<UIMovie>>
            by lazy { MutableLiveData<List<UIMovie>>() }

    val favoritesLiveData: LiveData<List<UIMovie>>
        get() = _favoritesLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData<String>() }

    val onError: LiveData<String>
        get() = _onError

    fun loadMovie(pageNum: Int = 1){
        MovieRepository.getFavouriteMovieList(this,pageNum)
    }

    override fun onMovieReady(movies: List<UIMovie>, totalPageNum: Int) {
        _favoritesLiveData.value = movies
    }

    override fun onMovieLoadingError(errorMsg: String) {
        _onError.value = errorMsg
    }


}