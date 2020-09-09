package com.example.androidproj1.UI

import android.os.Bundle
import androidx.activity.viewModels
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidproj1.R
import com.example.androidproj1.network.APIResponse
import com.example.androidproj1.recyclerview.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.movieLiveData.observe(this, Observer {
            bindMovieData(it)
        })

        viewModel.onError.observe(this, Observer {
            handlingErr(it)
        })

        viewModel.loadMovie()

        movieRecycler.addItemDecoration(DividerItemDecoration(this,1))

    }

    //Check the function of the button
    private fun bindMovieData (moviesResponse: APIResponse) {
        movieRecycler.adapter = MovieAdapter(moviesResponse.movies)

        fab.setOnClickListener {
            viewModel.loadMovie(true)
        }
    }

    private fun handlingErr(errMsg: String){
        Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show()
    }

}
