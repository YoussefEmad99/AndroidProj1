package com.example.androidproj1.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.DataBase.MovieDB
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*
import com.example.androidproj1.fragments.FragmentA
import com.example.androidproj1.repository.MovieRepository
import com.example.androidproj1.repository.MovieRepository.appDatabase


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
    //private  var appDatabase: MovieDB = MovieDB.getDatabase(application)

    private var movieName: TextView = itemView.moviename    //removed because of removing title (new design)
    var movieDescription: TextView = itemView.moviedescription
    var movieImage: ImageView = itemView.movieimage
    var progressBar: ProgressBar = itemView.progressBar
    var favButton: ImageButton = itemView.favButton


    @SuppressLint("SetTextI18n")
    fun initialize(item: UIMovie) {
        movieName.text = item.title   //removed because of removing title (new design)
        movieDescription.text = "${item.popularity}"

        Picasso.get().load("$imageBaseUrl${item.imgPath}").into(movieImage)
        progressBar.progress = (item.popularity * 10).toInt()
        if (item.is_fav) {favButton.setImageResource(R.drawable.ic_baseline_favorite_24)}
        //-----------------arguably the greatest code ever written------------------
        //var fav: BooleanArray = BooleanArray(200) {i-> false} //using kotlin super powers to initalize in ONE AND ONLY LINE
        favButton.setOnClickListener{v: View ->
            var position: Int = bindingAdapterPosition
            if (item.is_fav == false ){
                item.is_fav= true
                favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(itemView.context, "Added ${movieName.text} to favourites",Toast.LENGTH_SHORT).show()
            }
            else{
                item.is_fav= false
                favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Toast.makeText(itemView.context, "Removed ${movieName.text} from favourites",Toast.LENGTH_SHORT).show()
            }





        }
        //view.movieimage.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_fragmentA_to_detailedPage)}

    }



}