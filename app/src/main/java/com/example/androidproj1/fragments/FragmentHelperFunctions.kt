package com.example.androidproj1.fragments

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.recyclerview.MovieAdapter

fun bindMovieData(movies: List<UIMovie>, movieRecycler: RecyclerView, loadingBar: ProgressBar?,
                  actionFunction: (MovieDetails) -> NavDirections
) {
    movieRecycler.adapter = MovieAdapter(movies, actionFunction)
    loadingBar?.visibility = View.GONE
}

fun handlingErr(errMsg: String, context: Context?, loadingBar: ProgressBar?) {
    Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show()
    loadingBar?.visibility = View.GONE
}

fun isLastItemDisplaying(recyclerView: RecyclerView): Boolean {
    if (recyclerView.adapter?.itemCount != 0) {
        val lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
        if (lastVisibleItemPosition != RecyclerView.NO_POSITION &&
            lastVisibleItemPosition == recyclerView.adapter?.itemCount?.minus(1)
        )
            return true
    }
    return false
}