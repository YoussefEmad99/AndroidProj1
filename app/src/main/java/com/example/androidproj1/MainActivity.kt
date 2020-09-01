package com.example.androidproj1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproj1.databinding.ActivityMainBinding
import com.example.androidproj1.network.*
import com.example.androidproj1.recyclerview.MovieAdapter
import com.example.androidproj1.recyclerview.MovieDetailsActivity
import com.example.androidproj1.recyclerview.Movies
import com.example.androidproj1.recyclerview.OnMovieItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMovieItemClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var movielist: ArrayList<Movies>
//    lateinit var movieImagelist: ArrayList<Image> // we deleted APIImageResponse and won't be used

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(R.layout.activity_main)
        movielist = ArrayList()
//        movieImagelist = ArrayList()




         val apiInterface : APIInterface by lazy{
            RetrofitClient.getClient().create(APIInterface::class.java)
        }
//        apiInterface.getMovieImage("605116", "9011f3fdc6551ebe547f181c79680b66").
//        enqueue(object : Callback<APIImageResponse> {
//            override fun onFailure(call: Call<APIImageResponse>, t: Throwable) {
//                Log.d("MainActivity", t.cause.toString())
//            }
//
//            override fun onResponse(
//                call: Call<APIImageResponse>,
//                response: Response<APIImageResponse>
//            ) {
//                Log.d("MainActivity", response.body()?.images?.first().toString())
//                for (i in 0..response.body()!!.images.size){
//                   getMovieImage(response.body()!!.images[i].filePath)
//                }
//            }
//
//        })
        apiInterface.getMovies("9011f3fdc6551ebe547f181c79680b66").
                enqueue(object : Callback<APIResponse> {
                    override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                        Log.d("MainActivity", t.cause.toString())
                    }

                    override fun onResponse(
                        call: Call<APIResponse>,
                        response: Response<APIResponse>
                    ) {
                        Log.d("MainActivity", response.body()?.movies?.first().toString())
//                        Log.d("MainActivity", response.body()?.movies?.first().toString())
                        for (i in response.body()!!.movies.indices) {
                            addMovies((response.body()!!.movies[i].movieName),
                                (response.body()!!.movies[i].popularity),
                                (response.body()!!.movies[i].imageURL))
                        }

                    }

                })
        movieRecycler.layoutManager = LinearLayoutManager(this)
        movieRecycler.addItemDecoration(DividerItemDecoration(this, 1))
        movieRecycler.adapter = MovieAdapter(movielist, this)
    }
    fun addMovies(name: String, popularity: Double, image: String) // changed
    {
      movielist.add(Movies(name, popularity, image))
    }
//    fun getMovieImage(filePath: String){ // to get movie Images / filepaths
//        movieImagelist.add(Image(filePath))
//    }
    override fun onItemClick(item: Movies, position: Int){
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("MOVIENAME", item.name)
        intent.putExtra("MOVIEESC", item.popularity)
        intent.putExtra("MOVIEIMAGE", item.image.toString())
        startActivity(intent)
    }
}
