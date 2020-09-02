package com.example.androidproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproj1.databinding.ActivityMainBinding
import com.example.androidproj1.recyclerview.MovieAdapter
import com.example.androidproj1.recyclerview.MovieDetailsActivity
import com.example.androidproj1.recyclerview.Movies
import com.example.androidproj1.recyclerview.OnMovieItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView.LayoutManager as LayoutManager1

class MainActivity : AppCompatActivity(), OnMovieItemClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var movielist: ArrayList<Movies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(R.layout.activity_main)
        movielist = ArrayList()
        addMovies()





        //----------------------NEWTWORK---------------------
//        val apiInterface : APIInterface by lazy{
//            RetrofitClient.getClient().create(APIInterface::class.java)
//        }
//        apiInterface.getMovieImage("605116", "9011f3fdc6551ebe547f181c79680b66").
//        enqueue(object: Callback<APIImageResponse> {
//            override fun onFailure(call: Call<APIImageResponse>, t: Throwable) {
//                Log.d("MainActivity", t.cause.toString())
//            }
//
//            override fun onResponse(
//                call: Call<APIImageResponse>,
//                response: Response<APIImageResponse>
//            ) {
//                Log.d("MainActivity", response.body()?.images?.first().toString())
//            }
//
//        })
//        apiInterface.getMovies("9011f3fdc6551ebe547f181c79680b66").
//                enqueue(object : Callback<APIResponse> {
//                    override fun onFailure(call: Call<APIResponse>, t: Throwable) {
//                        Log.d("MainActivity", t.cause.toString())
//                    }
//
//                    override fun onResponse(
//                        call: Call<APIResponse>,
//                        response: Response<APIResponse>
//                    ) {
//                        Log.d("MainActivity", response.body()?.movies?.first().toString())
//                    }
//
//                })
        movieRecycler.LayoutManager = LinearLayoutManager(this)
        movieRecycler.addItemDecoration(DividerItemDecoration(this,1))
        movieRecycler.adapter = MovieAdapter(movielist,this)
    }
    fun addMovies()
    {
      movielist.add(Movies("get name from api", "get description from api", "GET AN IMAGE LINK"))
    }
    override fun onItemClick(item: Movies, position: Int){
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("MOVIENAME", item.name)
        intent.putExtra("MOVIEESC", item.description)
        intent.putExtra("MOVIEIMAGE", item.image.toString())
        startActivity(intent)
    }
}
