package com.example.androidproj1.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter(private var items: List<UIMovie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.initialize(items[position])
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageBaseUrl = "https://image.tmdb.org/t/p/w200"

    //    private var movieName: TextView = itemView.moviename    removed because of removing title (new design)
    var movieDescription: TextView = itemView.moviedescription
    var movieImage: ImageView = itemView.movieimage
    var progressBar: ProgressBar = itemView.progressBar
    var isClicked = false

    fun initialize(item: UIMovie) {
//        movieName.text = item.title   removed because of removing title (new design)
        movieDescription.text = "${item.popularity * 10}%"
        Picasso.get().load("$imageBaseUrl${item.imgPath}")
            .into(movieImage)
        progressBar.progress = (item.popularity * 10).toInt()

        itemView.favButton.setOnClickListener {
            //TODO: change image resource of list item when it is favourited
            isClicked = if(isClicked){
                itemView.favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                Log.i("Movie Adapter", "is clicked: $isClicked\n is enabled: ${itemView.favButton.isEnabled}")
                true
            } else{
                itemView.favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                false
            }
        }
    }

}