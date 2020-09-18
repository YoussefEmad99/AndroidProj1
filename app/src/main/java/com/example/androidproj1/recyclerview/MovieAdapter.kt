package com.example.androidproj1.recyclerview

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*
import com.example.androidproj1.fragments.FragmentA



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

    //  private var movieName: TextView = itemView.moviename    removed because of removing title (new design)
    var movieDescription: TextView = itemView.moviedescription
    var movieImage: ImageView = itemView.movieimage
    var progressBar: ProgressBar = itemView.progressBar
    var favButton: ImageButton = itemView.favButton

    @SuppressLint("SetTextI18n")
    fun initialize(item: UIMovie) {
//      movieName.text = item.title   removed because of removing title (new design)
        movieDescription.text = "${item.popularity}"
        Picasso.get().load("$imageBaseUrl${item.imgPath}").into(movieImage)
        progressBar.progress = (item.popularity * 10).toInt()

        //-----------------arguably the greatest code ever written------------------
        var fav: BooleanArray = BooleanArray(200) {i-> false} //using kotlin super powers to initalize in ONE AND ONLY LINE
        favButton.setOnClickListener{v: View ->
            var position: Int = bindingAdapterPosition
            if (!fav[position]){
                fav[position]=true
                favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(itemView.context, "Added movie number ${position+1} to favourites",Toast.LENGTH_SHORT).show()
            }
            else{
                fav[position]=false
                favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Toast.makeText(itemView.context, "Removed movie number ${position+1} from favourites",Toast.LENGTH_SHORT).show()
            }



        }
    }


}