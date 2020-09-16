package com.example.androidproj1.fragments

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.UI.MainViewModel
import com.example.androidproj1.recyclerview.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*

class FragmentA : Fragment() {

    private var recyclerState: Parcelable? = null
    private var loadingBar: ProgressBar? = null
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //reference to the loading bar
        loadingBar = activity?.loading_bar
        //view model of the current fragment
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            bindMovieData(it)
            movieRecycler.layoutManager?.onRestoreInstanceState(recyclerState)
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer {
            handlingErr(it)
            loadingBar?.visibility = View.GONE
        })

        //load data and show indefinite loading progress bar
        loadingBar?.visibility = View.VISIBLE
        viewModel.loadMovie()

        activity?.fab?.setOnClickListener {
            loadingBar?.visibility = View.VISIBLE
            viewModel.loadMovie()
        }

        movieRecycler.setOnScrollChangeListener{ recyclerView, _, _, _, _ ->
            if(recyclerView !is RecyclerView)
                return@setOnScrollChangeListener

            if(isLastItemDisplaying(movieRecycler)){
                page += 1
                recyclerState = movieRecycler.layoutManager?.onSaveInstanceState()
                loadingBar?.visibility = View.VISIBLE
                viewModel.loadMovie(page)

            }
        }

    }

    private fun bindMovieData(movies: List<UIMovie>) {
        movieRecycler.adapter = MovieAdapter(movies)
        loadingBar?.visibility = View.GONE
    }

    private fun handlingErr(errMsg: String) {
        Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show()
        loadingBar?.visibility = View.GONE
    }

    private fun isLastItemDisplaying(recyclerView: RecyclerView): Boolean {
        if (recyclerView.adapter?.itemCount != 0) {
            val lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION &&
                lastVisibleItemPosition == recyclerView.adapter?.itemCount?.minus(1)
            )
                return true
        }
        return false
    }
}