package com.example.androidproj1.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.recyclerview.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel.movieLiveData.observe(this, Observer {
//            bindMovieData(it)
//        })
//
//        viewModel.onError.observe(this, Observer {
//            handlingErr(it)
//        })
//
//        //load data and show indefinite loading progress bar
//        viewModel.loadMovie()
//        loading_bar.visibility = View.VISIBLE
//
//        fab.setOnClickListener {
//            viewModel.loadMovie(true)
//            loading_bar.visibility = View.VISIBLE
//        }
//
//        movieRecycler.addItemDecoration(DividerItemDecoration(this,1))
    }

//    private fun bindMovieData (movies: List<UIMovie>) {
//        movieRecycler.adapter = MovieAdapter(movies)
//        loading_bar.visibility = View.GONE
//    }
//
//    private fun handlingErr(errMsg: String){
//        Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show()
//        loading_bar.visibility = View.GONE
//    }

}
