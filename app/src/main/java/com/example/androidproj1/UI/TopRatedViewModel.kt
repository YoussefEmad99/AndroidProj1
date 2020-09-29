package com.example.androidproj1.UI
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.repository.MovieRepository

class TopRatedViewModel(application: Application) : AndroidViewModel(application), MovieRepository.MovieCallback {

    private lateinit var topRatedData: List<UIMovie>

    private val _topRatedLiveData : MutableLiveData<List<UIMovie>>
            by lazy { MutableLiveData<List<UIMovie>>() }

    val topRatedLiveData: LiveData<List<UIMovie>>
        get() = _topRatedLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData<String>() }
    val onError: LiveData<String>
        get() = _onError

    init {
        MovieRepository.createDatabase(application)
    }

    //Check this one
    fun loadMovie(pageNum: Int = 1){
        MovieRepository.requestTopRated(this, pageNum)
    }

    override fun onMovieReady(movies: List<UIMovie>, totalPageNum: Int) {
        topRatedData = if(this::topRatedData.isInitialized)
            topRatedData + movies
        else
            movies
        _topRatedLiveData.value = topRatedData

    }

    override fun onMovieLoadingError(errorMsg: String) {
        _onError.value = errorMsg
    }
}