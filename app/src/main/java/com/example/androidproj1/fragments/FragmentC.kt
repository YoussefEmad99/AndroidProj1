package com.example.androidproj1.fragments

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
 import com.example.androidproj1.R
import com.example.androidproj1.UI.FavoritesViewModel
import com.example.androidproj1.UI.MainViewModel
import com.example.androidproj1.fragments.FragmentCDirections.Companion.actionFragmentCToDetailedPage
import com.example.androidproj1.repository.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*

class FragmentC : Fragment(){

    private var recyclerState: Parcelable? = null
    private var loadingBar: ProgressBar? = null

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
        val viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        viewModel.favoritesLiveData.observe(viewLifecycleOwner, Observer {
            bindMovieData(it, movieRecycler, loadingBar, ::actionFragmentCToDetailedPage)
            movieRecycler.layoutManager?.onRestoreInstanceState(recyclerState)
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer {
            handlingErr(it, context, loadingBar)
            loadingBar?.visibility = View.GONE
        })

        //load data and show indefinite loading progress bar
        loadingBar?.visibility = View.VISIBLE
        viewModel.loadMovie()

        activity?.fab?.setOnClickListener {
            loadingBar?.visibility = View.VISIBLE
            viewModel.loadMovie()
        }

    }

    override fun onResume() {
        super.onResume()
        restoreState(activity?.fab)
    }
}